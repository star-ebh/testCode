package com.example.tiapi;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 星星泡面
 * @version 1.0.0
 * @Description 订单详情
 * @createTime 2022年03月04日 12:09:00
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

    /**
     * 客户的唯一采购订单号
     * minLength : 1
     * maxLength : 35
     */
    @JSONField(name = "CustomerPurchaseOrderIdentifier")
    private String customerPurchaseOrderIdentifier;

    /**
     * 客户的采购订单日期；如果未提供，将默认为当前日期。
     * 最小长度：32
     * 最大长度：32
     * 值必须是 ISO 8601 格式的有效 UTC 日期：yyyy-MM-dd'T'HH:mm:ss'Z'
     * 例:2020-12-31T13:56:00Z
     */
    @JSONField(name = "CustomerPurchaseOrderDate")
    private String customerPurchaseOrderDate;

    /**
     * Apruve 帐户 ID 字符串
     * 最小长度：32
     * 最大长度：32
     * 示例 b526e312fe42031d43f1e112b134a1e0
     */
    @JSONField(name = "ApruveAccountId")
    private String apruveAccountId;

    /**
     * 出售标识符
     * minLength : 1
     * maxLength : 17
     */
    @JSONField(name = "CustomerSoldToIdentifier")
    private String customerSoldToIdentifier;

    /**
     * 运送至标识符
     * 最大长度：17
     */
    @JSONField(name = "CustomerShipToIdentifier")
    private String customerShipToIdentifier;

    /**
     * 最终客户标识符
     * 最大长度：17
     */
    @JSONField(name = "CustomerEndCustomerIdentifier")
    private String customerEndCustomerIdentifier;

    /**
     * 账单至标识符
     * 最大长度：4000
     */
    @JSONField(name = "CustomerBillToIdentifier")
    private String customerBillToIdentifier;

    /**
     * 付款人标识符
     * 最大长度：4000
     */
    @JSONField(name = "CustomerPayerIdentifier")
    private String customerPayerIdentifier;

    /**
     * 客户订单属性 可空
     */
    @JSONField(name = "CustomerOrderAttributes")
    private List<Attribute> customerOrderAttributes;

    /**
     * 要求的运输
     */
    @JSONField(name = "CustomerDesiredShipping")
    private List<Ship> customerDesiredShipping;

    /**
     * 订单项
     */
    @JSONField(name = "LineItems")
    private List<OrderItem> lineItems;


    /**
     * 属性类
     */
    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Attribute {

        /**
         * 可空
         * 属性值
         * 最大长度：60
         * 客户传递文本。
         * 文本将按原样在响应中传回。
         */
        @JSONField(name = "Attribute")
        private String attribute;
    }

    /**
     * 运送方式类
     */
    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Ship {
        /**
         * 供应商发货地：DFW
         */
        @JSONField(name = "SupplierShipFrom")
        private String supplierShipFrom;

        /**
         * 运营商服务水平:CUS1
         */
        @JSONField(name = "CarrierServiceLevel")
        private String carrierServiceLevel;
    }

    /**
     * 订单项类
     */
    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class OrderItem {
        /**
         * 订单行项目编号(必传)
         */
        @JSONField(name = "CustomerOrderLineItemNumber")
        private Integer customerOrderLineItemNumber;

        /**
         * 供应商产品标识符(必传)
         * minLength : 1
         * maxLength : 35
         */
        @JSONField(name = "SupplierProductIdentifier")
        private String supplierProductIdentifier;

        /**
         * 产品标识符(可空)
         * 最大长度：25
         */
        @JSONField(name = "CustomerProductIdentifier")
        private String customerProductIdentifier;

        /**
         * 自定义卷轴指示器(可空)
         * 默认为false
         */
        @JSONField(name = "CustomReelIndicator")
        private Boolean customReelIndicator;

        /**
         * 客户要求数量(必传)
         */
        @JSONField(name = "CustomerRequestedQuantity")
        private Integer customerRequestedQuantity;

        /**
         * 客户项目属性(可空)
         */
        @JSONField(name = "CustomerItemAttributes")
        private List<Attribute> customerItemAttributes;
    }
}
