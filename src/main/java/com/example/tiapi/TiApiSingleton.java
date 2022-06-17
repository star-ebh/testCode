package com.example.tiapi;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TiApiSingleton
 * @Description 德州仪器API单例 生产默认100,0000
 * @Author 星星泡面
 * @Date 2022/3/3 23:03
 * @Version 1.0
 **/
@Slf4j
public class TiApiSingleton {

    private final TimedCache<String, String> timedCache;

    /**
     * 沙盒url
     **/
    private final static String BASE_URL = "https://transact-pre.ti.com/v1";
    /**
     * 授权url
     **/
    private final static String OAUTH_URL = "/oauth/accesstoken";
    /**
     * 商店产品url
     **/
    private final static String STORE_PRODUCTS_URL = "/store/products/";
    /**
     * 创建订单url
     */
    private final static String STORE_CREATE_ORDER_URL = "/store/orders/";
    /**
     * 货币编码
     **/
    private final static String CODE = "CNY";
    /**
     * 全局超时时间
     **/
    private final int TIMEOUT = 60000;
    /**
     * 订单对象Orders字段名
     */
    public final static String KEY_ORDER_OBJ = "Orders";
    /**
     * 库存key
     */
    public final static String KEY_QUANTITY = "Quantity";
    /**
     * 产品标识符
     */
    public final static String KEY_PRODUCT_IDENTIFIER = "ProductIdentifier";
    /**
     * 订单返回信息键值
     */
    public final static String KEY_ORDER_MESSAGES = "OrderMessages";
    /**
     * key
     */
    private final static String CLIENT_ID = "ojxYrJU7Aec2vVcCKcHZ4hRwA8mJNzAU";
    /**
     * secret
     */
    private final static String CLIENT_SECRET = "4BBkxnwgHUnaoIaL";
    /**
     * token缓存key
     */
    private final static String KEY_TOKEN = "AccessToken";
    /**
     * 缓存过期时间
     */
    private final static long CACHE_TIMEOUT = 55 * 60 * 1000;

    private TiApiSingleton() {
        this.timedCache = CacheUtil.newTimedCache(CACHE_TIMEOUT);
    }

    private enum TiApiEnum {
        /**
         * 实例
         */
        INSTANCE;

        private final TiApiSingleton instance;

        TiApiEnum() {
            instance = new TiApiSingleton();
        }

        public TiApiSingleton getInstance() {
            return instance;
        }
    }

    public static TiApiSingleton getInstance() {
        return TiApiEnum.INSTANCE.getInstance();
    }

    /**
     * 获取最新的token
     *
     * @return token
     */
    public String getAccessToken() throws Exception {
        try {
            if (timedCache.get(KEY_TOKEN, false) != null) {
                return timedCache.get(KEY_TOKEN, false);
            }
            Map<String, Object> formMap = new HashMap<>(3);
            formMap.put("grant_type", "client_credentials");
            formMap.put("client_id", CLIENT_ID);
            formMap.put("client_secret", CLIENT_SECRET);
            String postResponse = HttpRequest.post(String.format("%s%s", BASE_URL, OAUTH_URL))
                    .contentType("application/x-www-form-urlencoded")
                    .form(formMap)
                    .timeout(TIMEOUT)
                    .execute().body();
            // token序列化为对象
            TiOauthResult tiOauthResult = JSONObject.parseObject(postResponse, TiOauthResult.class);
            log.info("{}", tiOauthResult);
            timedCache.put(KEY_TOKEN, tiOauthResult.getAccessToken(), CACHE_TIMEOUT);
            // 拿到access_token
            return tiOauthResult.getAccessToken();
        } catch (Exception e) {
            throw new Exception("获取最新token错误");
        }
    }

    /**
     * 查询库存数
     *
     * @param genericPartNumber 部件号
     * @return 库存数
     */
    public Integer queryInventory(String genericPartNumber) throws Exception {
        JSONObject storeProductObj = queryProductDetails(genericPartNumber);
        // 获取库存
        return storeProductObj.getInteger(KEY_QUANTITY);
    }

    private static final String ERRORS_STR = "Errors";

    /**
     * 查询商品对象
     *
     * @param genericPartNumber 部件号
     * @return 商品对象
     */
    public JSONObject queryProductDetails(String genericPartNumber) throws Exception {
        // 查询库存
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("currency", CODE);
        String response = HttpRequest.get(String.format("%s%s%s", BASE_URL, STORE_PRODUCTS_URL, genericPartNumber))
                .form(paramMap)
                .timeout(TIMEOUT)
                .bearerAuth(getAccessToken())
                .execute().body();
        JSONObject responseObj = JSONObject.parseObject(response);
        log.info("{}", responseObj);
        if (ObjectUtil.isNotNull(responseObj.getJSONArray(ERRORS_STR))) {
            throw new Exception("查询出错了");
        }
        // 序列化为对象
        return responseObj;
    }

    /**
     * 创建订单
     *
     * @param data 参数
     * @return 创建订单返回对象
     */
    public JSONObject createStoreOrder(JSONObject data) throws Exception {
        String response = HttpRequest.post(String.format("%s%s", BASE_URL, STORE_CREATE_ORDER_URL))
                .body(data.toJSONString(), "application/json")
                .timeout(TIMEOUT)
                .bearerAuth(getAccessToken())
                .execute()
                .body();
        // 序列化为对象
        return JSONObject.parseObject(response);
    }

}
