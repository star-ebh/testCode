package com.example.tiapi;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 星星泡面
 * @version 1.0.0
 * @Description Ti Oauth api返回实体
 * @createTime 2022年03月03日 18:05:00
 */
@Data
@NoArgsConstructor
public class TiOauthResult {
    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "token_type")
    private String tokenType;

    @JSONField(name = "expires_in")
    private Integer expiresIn;

    @JSONField(name = "scope")
    private String scope;

    @JSONField(name = "application_name")
    private String applicationName;

    @JSONField(name = "issued_at")
    private String issuedAt;

    @JSONField(name = "client_id")
    private String clientId;
}
