package com.yiancode.fastauthdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * FastAuth 微信公众号配置
 */
@Configuration
@ConfigurationProperties(prefix = "fastauth.wechat-mp")
public class FastAuthConfig {

    /**
     * 公众号 AppID
     */
    private String clientId;

    /**
     * 公众号 AppSecret
     */
    private String clientSecret;

    /**
     * 回调地址
     */
    private String redirectUri;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
