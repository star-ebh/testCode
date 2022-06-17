package com.example.tiapi;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 星星泡面
 * @version 1.0.0
 * @Description 定时任务单例
 * @createTime 2022年03月04日 10:44:00
 */
@Slf4j
public class TaskSingleton {
    /**
     * 定时任务
     * key:产品基本部件号
     * value: PurchaseDetails
     */
    private final Map<String, TaskDetails> taskMap;

    private TaskSingleton() {
        this.taskMap = new ConcurrentHashMap<>();
        timedTask();
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class TaskDetails {
        /**
         * 需求数量
         */
        private int requiredQuantity;
        /**
         * 已采购数量
         */
        private int orderQuantity;

        /**
         * 订单详情
         */
        private OrderDetails orderDetails;

        /**
         * 是否结束任务
         *
         * @return yes or no
         */
        public boolean isOverTask() {
            return (orderQuantity == requiredQuantity) || (orderQuantity > requiredQuantity);
        }

        /**
         * 获取剩余需求数量
         *
         * @return 余数
         */
        public int getRemainQuantity() {
            return requiredQuantity - orderQuantity;
        }
    }

    private enum TaskEnum {
        /**
         * 实例
         */
        INSTANCE;

        private final TaskSingleton instance;

        TaskEnum() {
            instance = new TaskSingleton();
        }

        public TaskSingleton getInstance() {
            return instance;
        }
    }

    /**
     * 获取实例
     *
     * @return TaskSingleton
     */
    public static TaskSingleton getInstance() {
        return TaskEnum.INSTANCE.getInstance();
    }

    /**
     * 全局定时任务
     */
    private void timedTask() {
        CronUtil.schedule("0/5 * * * * ? *", (Task) () -> {
            try {
                // 循环
                for (Map.Entry<String, TaskDetails> entry : taskMap.entrySet()) {
                    // 零件号
                    String genericPartNumber = entry.getKey();
                    // 任务详情
                    TaskDetails taskDetails = entry.getValue();
                    // 查询产品信息
                    JSONObject productDetails = TiApiSingleton.getInstance().queryProductDetails(genericPartNumber);
                    // 获取库存
                    Integer quantity = productDetails.getInteger(TiApiSingleton.KEY_QUANTITY);
                    if (quantity > 0) {
                        // 要下单数量
                        int orderNumber = quantity > taskDetails.getRemainQuantity() ? taskDetails.getRemainQuantity() : quantity;
                        JSONObject requestData = constructOrderDetails(orderNumber, productDetails);
                        log.info("创建订单请求数据->{}", requestData);
                        // 发起下单请求
                        final JSONObject response = TiApiSingleton.getInstance().createStoreOrder(requestData);
                        // TODO 成功判定待修改 是否保存返回值? 这句是否保存？
                        JSONArray resJsonArray = response.getJSONArray(TiApiSingleton.KEY_ORDER_MESSAGES);
                        if (ObjectUtil.isNotNull(response.getJSONObject("Errors"))) {
                            log.error("自动下单失败了->{}", response);
                        } else if (ObjectUtil.isNotNull(resJsonArray)) {
                            // 状态400
                            isResponseError(response, resJsonArray);
                        } else if (ObjectUtil.isNotNull(response.getJSONArray("Orders"))) {
                            // 状态500
                            JSONArray orders = response.getJSONArray("Orders");
                            if (ObjectUtil.isNotNull(orders)) {
                                for (int i = 0; i < orders.size(); i++) {
                                    if (ObjectUtil.isNotNull(orders.getJSONObject(i).getJSONArray(TiApiSingleton.KEY_ORDER_MESSAGES))) {
                                        isResponseError(response, orders.getJSONObject(i).getJSONArray(TiApiSingleton.KEY_ORDER_MESSAGES));
                                    }
                                }
                            }
                        } else {
                            log.error("自动下单失败了->{}", response);
                            throw new Exception("自动下单失败");
                        }

                        taskDetails.setOrderQuantity(taskDetails.getOrderQuantity() + orderNumber);
                        if (taskDetails.isOverTask()) {
                            // 移除任务
                            removeTask(genericPartNumber);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("自动下单失败了->", e);
            }
        });
        // 支持秒级别定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }

    private void isResponseError(JSONObject response, JSONArray resJsonArray) throws Exception {
        for (int i = 0; i < resJsonArray.size(); i++) {
            JSONObject resItem = resJsonArray.getJSONObject(i);
            // TODO 成功判定待修改
            if ("ERROR".equals(resItem.getString("Type"))) {
                log.error("自动下单失败了->{}", response);
                throw new Exception("自动下单失败了");
            }
        }
    }

    /**
     * 添加任务
     *
     * @param key         键值
     * @param taskDetails 任务详情
     */
    public TaskSingleton addTask(String key, TaskDetails taskDetails) {
        try {
            if (taskMap.containsKey(key)) {
                throw new Exception("已存在同产品任务");
            }
            taskMap.put(key, taskDetails);
            log.info(String.valueOf(taskMap));
            return getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加任务错误->", e);
            return null;
        }
    }

    /**
     * 移除任务
     *
     * @param key 键值
     */
    public void removeTask(String key) {
        try {
            taskMap.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("移除任务错误->", e);
        }
    }

    /**
     * 构建订单请求结构体
     *
     * @param orderNumber    库存数
     * @param productDetails 产品信息
     * @return OrderDetails
     */
    private JSONObject constructOrderDetails(Integer orderNumber, JSONObject productDetails) {
        // 构建请求数据
        JSONObject requestData = new JSONObject();
        // 构建客户订单属性List
        List<OrderDetails.Attribute> attributes = new ArrayList<>();
        // TODO 模拟订单属性
        attributes.add(new OrderDetails.Attribute().setAttribute("PO: 23423"));
        // TODO 模拟订单属性
        attributes.add(new OrderDetails.Attribute().setAttribute("Authorized by David M. (04/21)"));

        // 构建客户期望的运输List
        List<OrderDetails.Ship> ships = new ArrayList<>();
        // TODO 模拟客户期望的运输
        ships.add(new OrderDetails.Ship().setSupplierShipFrom("DFW").setCarrierServiceLevel("CUS1"));

        // 构建订单项
        List<OrderDetails.OrderItem> orderItems = new ArrayList<>();
        // TODO 模拟订单.客户项目属性List
        List<OrderDetails.Attribute> itemAttributes = new ArrayList<>();
        itemAttributes.add(new OrderDetails.Attribute().setAttribute("RE: For Manchaster Plant"));
        // TODO 模拟订单项
        OrderDetails.OrderItem orderItem = new OrderDetails.OrderItem()
                .setCustomerOrderLineItemNumber(1)
                .setSupplierProductIdentifier(productDetails.getString(TiApiSingleton.KEY_PRODUCT_IDENTIFIER))
//                .setCustomerProductIdentifier("")
//                .setCustomReelIndicator(false)
                .setCustomerRequestedQuantity(orderNumber);
//                .setCustomerItemAttributes(itemAttributes);
        orderItems.add(orderItem);

        // 构建订单主体数据
        OrderDetails orderDetails = new OrderDetails()
//                .setCustomerPurchaseOrderIdentifier(IdUtil.simpleUUID())
//                .setApruveAccountId("Apruve 帐户 ID")
//                .setCustomerSoldToIdentifier("客户出售标识符")
//                .setCustomerShipToIdentifier("客户发货至标识符")
//                .setCustomerEndCustomerIdentifier("客户的最终客户标识符")
//                .setCustomerBillToIdentifier("客户账单至标识符")
//                .setCustomerPayerIdentifier("客户付款人标识符")
//                .setCustomerOrderAttributes(attributes)
//                .setCustomerDesiredShipping(ships)
                .setLineItems(orderItems);
        requestData.put(TiApiSingleton.KEY_ORDER_OBJ, orderDetails);
        return requestData;
    }
}
