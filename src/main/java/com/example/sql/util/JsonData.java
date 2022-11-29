package com.example.sql.util;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuzhou
 * @version 1.0.0
 * @since 2022-11-25  11:58
 */
@NoArgsConstructor
@Data
public class JsonData {

    @SerializedName("id")
    private String id;
    @SerializedName("productId")
    private String productId;
    @SerializedName("orderId")
    private String orderId;
    @SerializedName("bomId")
    private String bomId;
    @SerializedName("machineCode")
    private String machineCode;
    @SerializedName("sn")
    private String sn;
    @SerializedName("macAddress")
    private String macAddress;
    @SerializedName("createBy")
    private String createBy;
    @SerializedName("createTime")
    private String createTime;
    @SerializedName("updateBy")
    private Object updateBy;
    @SerializedName("updateTime")
    private Object updateTime;

    public String toSqlString() {
        return "update data_sn_mac_info set machine_code = '" + machineCode + "'" +
                " where internal_sn = '" + sn + "';" ;
    }
    public String toJsonString() {
        return "'" + sn + "'" +
                ": '" + machineCode + "'," ;
    }
}
