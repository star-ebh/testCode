package com.example.json;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class MoKaWebhooksDTO implements Serializable {
    private static final long serialVersionUID = 5926602964923520254L;

    /**
     * ID
     */
    private String id;

    /**
     * 事件 pushCandidate
     */
    private String event;

    /**
     * timestamp 触发时间
     */
    private Long triggeredAt;

    /**
     * 实际数据
     */
    private JSONObject data;
}
