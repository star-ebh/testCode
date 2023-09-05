package com.example.json;

/**
 * @Description: 通用常量
 * @author: jeecg-boot
 */
public interface CommonConstant {

    /**
     * 正常状态
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    Integer DEL_FLAG_1 = 1;

    /**
     * 未删除
     */
    Integer DEL_FLAG_0 = 0;

    /**
     * 系统日志类型： 操作日志
     */
    int LOG_TYPE_0 = 0;

    /**
     * 系统日志类型： 登录
     */
    int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 操作
     */
    int LOG_TYPE_2 = 2;

    /**
     * 操作日志类型： 查询
     */
    int OPERATE_TYPE_1 = 1;

    /**
     * 操作日志类型： 添加
     */
    int OPERATE_TYPE_2 = 2;

    /**
     * 操作日志类型： 更新
     */
    int OPERATE_TYPE_3 = 3;

    /**
     * 操作日志类型： 删除
     */
    int OPERATE_TYPE_4 = 4;

    /**
     * 操作日志类型： 倒入
     */
    int OPERATE_TYPE_5 = 5;

    /**
     * 操作日志类型： 导出
     */
    int OPERATE_TYPE_6 = 6;

    /**
     * {@code 500 Server Error} (HTTP/1.0 - RFC 1945)
     */
    Integer SC_INTERNAL_SERVER_ERROR_500 = 500;

    /**
     * {@code 200 OK} (HTTP/1.0 - RFC 1945)
     */
    Integer SC_OK_200 = 200;

    /**
     * 访问权限认证未通过 510
     */
    Integer SC_JEECG_NO_AUTHZ = 510;

    /**
     * 登录用户Shiro权限缓存KEY前缀
     */
    String PREFIX_USER_SHIRO_CACHE = "shiro:cache:org.jeecg.config.shiro.ShiroRealm.authorizationCache:";

    /**
     * 登录用户Token令牌缓存KEY前缀
     */
    String PREFIX_USER_TOKEN = "prefix_user_token_";

    /**
     * 登录二维码
     */
    String LOGIN_QRCODE_PRE = "QRCODELOGIN:";

    String LOGIN_QRCODE = "LQ:";

    /**
     * 登录二维码token
     */
    String LOGIN_QRCODE_TOKEN = "LQT:";

    /**
     * 0：一级菜单
     */
    Integer MENU_TYPE_0 = 0;

    /**
     * 1：子菜单
     */
    Integer MENU_TYPE_1 = 1;

    /**
     * 2：按钮权限
     */
    Integer MENU_TYPE_2 = 2;

    /**
     * 通告对象类型（USER:指定用户，ALL:全体用户）
     */
    String MSG_TYPE_UESR = "USER";

    String MSG_TYPE_ALL = "ALL";

    /**
     * 发布状态（0未发布，1已发布，2已撤销）
     */
    String NO_SEND = "0";

    String HAS_SEND = "1";

    String HAS_CANCLE = "2";

    /**
     * 阅读状态（0未读，1已读）
     */
    String HAS_READ_FLAG = "1";

    String NO_READ_FLAG = "0";

    /**
     * 优先级（L低，M中，H高）
     */
    String PRIORITY_L = "L";

    String PRIORITY_M = "M";

    String PRIORITY_H = "H";

    /**
     * 短信模板方式 0 .登录模板、1.注册模板、2.忘记密码模板
     */
    String SMS_TPL_TYPE_0 = "0";

    String SMS_TPL_TYPE_1 = "1";

    String SMS_TPL_TYPE_2 = "2";

    /**
     * 状态(0无效1有效)
     */
    String STATUS_0 = "0";

    String STATUS_1 = "1";

    /**
     * 同步工作流引擎1同步0不同步
     */
    Integer ACT_SYNC_1 = 1;

    Integer ACT_SYNC_0 = 0;

    /**
     * 消息类型1:通知公告2:系统消息
     */
    String MSG_CATEGORY_1 = "1";

    String MSG_CATEGORY_2 = "2";

    /**
     * 是否配置菜单的数据权限 1是0否
     */
    Integer RULE_FLAG_0 = 0;

    Integer RULE_FLAG_1 = 1;

    /**
     * 是否用户已被冻结 1正常(解冻) 2冻结
     */
    Integer USER_UNFREEZE = 1;

    Integer USER_FREEZE = 2;

    /**
     * 字典翻译文本后缀
     */
    String DICT_TEXT_SUFFIX = "_dictText";

    /**
     * 文件上传类型（本地：local，Minio：minio，阿里云：alioss）
     */
    String UPLOAD_TYPE_LOCAL = "local";

    String UPLOAD_TYPE_MINIO = "minio";

    String UPLOAD_TYPE_OSS = "alioss";

    /**
     * 员工身份 （1:普通员工 2:上级）
     */
    Integer USER_IDENTITY_1 = 1;

    Integer USER_IDENTITY_2 = 2;

    /**
     * sys_user 表 username 唯一键索引
     */
    String SQL_INDEX_UNIQ_SYS_USER_USERNAME = "uniq_sys_user_username";

    /**
     * sys_user 表 work_no 唯一键索引
     */
    String SQL_INDEX_UNIQ_SYS_USER_WORK_NO = "uniq_sys_user_work_no";

    /**
     * sys_user 表 phone 唯一键索引
     */
    String SQL_INDEX_UNIQ_SYS_USER_PHONE = "uniq_sys_user_phone";

    /**
     * 达梦数据库升提示。违反表[SYS_USER]唯一性约束
     */
    String SQL_INDEX_UNIQ_SYS_USER = "唯一性约束";

    /**
     * sys_user 表 email 唯一键索引
     */
    String SQL_INDEX_UNIQ_SYS_USER_EMAIL = "uniq_sys_user_email";

    /**
     * sys_quartz_job 表 job_class_name 唯一键索引
     */
    String SQL_INDEX_UNIQ_JOB_CLASS_NAME = "uniq_job_class_name";

    /**
     * sys_position 表 code 唯一键索引
     */
    String SQL_INDEX_UNIQ_CODE = "uniq_code";

    /**
     * sys_role 表 code 唯一键索引
     */
    String SQL_INDEX_UNIQ_SYS_ROLE_CODE = "uniq_sys_role_role_code";

    /**
     * sys_depart 表 code 唯一键索引
     */
    String SQL_INDEX_UNIQ_DEPART_ORG_CODE = "uniq_depart_org_code";

    /**
     * sys_category 表 code 唯一键索引
     */
    String SQL_INDEX_UNIQ_CATEGORY_CODE = "idx_sc_code";

    String X_ACCESS_TOKEN = "X-Access-Token";

    String X_SIGN = "X-Sign";

    String X_TIMESTAMP = "X-TIMESTAMP";

    String TOKEN_IS_INVALID_MSG = "Token失效，请重新登录!";

    /**
     * 多租户 请求头
     */
    String TENANT_ID = "tenant-id";

    /**
     * 微服务读取配置文件属性 服务地址
     */
    String CLOUD_SERVER_KEY = "spring.cloud.nacos.discovery.server-addr";

    /**
     * 第三方登录 验证密码/创建用户 都需要设置一个操作码 防止被恶意调用
     */
    String THIRD_LOGIN_CODE = "third_login_code";

    /**
     * 系统通告消息状态：0=未发布
     */
    String ANNOUNCEMENT_SEND_STATUS_0 = "0";

    /**
     * 系统通告消息状态：1=已发布
     */
    String ANNOUNCEMENT_SEND_STATUS_1 = "1";

    /**
     * 系统通告消息状态：2=已撤销
     */
    String ANNOUNCEMENT_SEND_STATUS_2 = "2";

    /**
     * ONLINE 报表权限用 从request中获取地址栏后的参数
     */
    String ONL_REP_URL_PARAM_STR = "onlRepUrlParamStr";

    /**
     * POST请求
     */
    String HTTP_POST = "POST";

    /**
     * PUT请求
     */
    String HTTP_PUT = "PUT";

    /**
     * PATCH请求
     */
    String HTTP_PATCH = "PATCH";

    /**
     * 未知的
     */
    String UNKNOWN = "unknown";

    /**
     * 字符串http
     */
    String STR_HTTP = "http";

    /**
     * String 类型的空值
     */
    String STRING_NULL = "null";

    /**
     * .do
     */
    String SPOT_DO = ".do";

    String AUTHORIZATION = "Authorization";

    String BASIC = "Basic ";

    /**
     * 判空错误信息模板
     */
    String NOT_NULL_ERROR_MSG_TEMPLATE = "找不到对应的{}";

    /**
     * 判空错误信息模板
     */
    String ADD_ERROR_MSG_TEMPLATE = "{}添加失败";

    /**
     * 数字0
     */
    int QUANTITY_ZERO = 0;

    /**
     * 数字1
     */
    int QUANTITY_ONE = 1;

    /**
     * 数字2
     */
    int QUANTITY_TWO = 2;

    /**
     * 数字3
     */
    int QUANTITY_THREE = 3;

    /**
     * 数字10
     */
    int QUANTITY_TEN = 10;

    /**
     * 数字16
     */
    int SIX_TEEN = 16;

    /**
     * 数字2000
     */
    int TWO_THOUSAND = 2000;

    /**
     * 数字100
     */
    int ONE_HUNDRED = 100;

    /**
     * 空字符串
     */
    String EMPTY_STRING = "";

    /**
     * 字符串数字0
     */
    String QUANTITY_ZERO_STR = "0";

    /**
     * 字符串数字1
     */
    String QUANTITY_ONE_STR = "1";

    /**
     * 字符串数字2
     */
    String QUANTITY_TWO_STR = "2";

    /**
     * 字符串数字3
     */
    String QUANTITY_THREE_STR = "3";

    /**
     * 字符串数字4
     */
    String QUANTITY_FOUR_STR = "4";

    /**
     * 冒号字符串
     */
    String COLON_STRING = "";

    /**
     * 时间格式一
     */
    String DATE_FORMAT_ONE = "yyyy-MM-dd HH:mm:ss.SSS";

    String YEAR_MONTH_FORMAT = "yyyy-MM-dd HH:mm:ss";

    String OA_DEPT = "dept";

    String OA_ORG = "org";

    String TIME_ZONE = "GMT+8";

    String APP_KEY = "x-rs-appKey";

    String TIME_STAMP = "x-rs-timestamp";

    String NONCE = "x-rs-nonce";

    String SIG = "x-rs-signature";

    String SECRET = "x-rs-secret";

    String PARAM_SECRET = "secret-param";

    String BODY = "body";

    String REQUEST_URL = "request_url";

    String OK = "ok";

    String APP_KEY_EMPTY = "appKey为空";

    String TIME_STAMP_EMPTY = "timeStamp为空";

    String NONCE_EMPTY = "nonce为空";

    String SIGNATURE_EMPTY = "signature为空";

    String OUT_TIME = "timeStamp已超时";

    /**
     * sys_data_log表的类型 用于区别评论区域的日志数据
     */
    String DATA_LOG_TYPE_COMMENT = "comment";

    /**
     * sys_data_log表的类型 老的数据比较 类型都设置为json
     */
    String DATA_LOG_TYPE_JSON = "json";

    /**
     * 消息模板：markdown
     */
    String MSG_TEMPLATE_TYPE_MD = "5";

    /**
     * 短信验证码redis-key的前缀
     */
    String PHONE_REDIS_KEY_PRE = "phone_msg";

    /**
     * x转发方案
     */
    String X_FORWARDED_SCHEME = "X-Forwarded-Scheme";

    /**
     * 存储在线程变量里的动态表名
     */
    String DYNAMIC_TABLE_NAME = "DYNAMIC_TABLE_NAME";

    /**
     * 前端vue3版本Header参数名
     */
    String VERSION = "X-Version";

    /**
     * 邮箱消息中地址登录时地址后携带的token,需要替换成真实的token值
     */
    String LOGIN_TOKEN = "{LOGIN_TOKEN}";

    /**
     * 部门表唯一key，orgCode
     */
    String DEPART_KEY_ORG_CODE = "orgCode";

    /**
     * http:// http协议
     */
    String HTTP_PROTOCOL = "http://";

    /**
     * https:// https协议
     */
    String HTTPS_PROTOCOL = "https://";

    /**
     * 发消息 会传递一些信息到map
     */
    String NOTICE_MSG_SUMMARY = "NOTICE_MSG_SUMMARY";

    /**
     * 发消息 会传递一个业务ID到map
     */
    String NOTICE_MSG_BUS_ID = "NOTICE_MSG_BUS_ID";

    /**
     * *号
     */
    String STAR = "*";
}
