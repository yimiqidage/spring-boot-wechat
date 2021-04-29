package io.github.limengning.wechat.accesstoken;

import io.github.limengning.wechat.WechatResponseBase;

public class AccessTokenResponse extends WechatResponseBase {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    private int expiresIn;
}
