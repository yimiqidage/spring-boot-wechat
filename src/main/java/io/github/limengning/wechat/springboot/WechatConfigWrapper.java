package io.github.limengning.wechat.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

import io.github.limengning.wechat.WechatConfig;

@ConfigurationProperties("wechat")
public class WechatConfigWrapper implements WechatConfig {
    private String appid;
    private String secret;
    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }


}
