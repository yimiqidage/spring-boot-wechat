package com.limengning.wechat;

import io.github.limengning.

public class AccessTokenService {

    private final String grantType;
    private final String appid;
    private final String secret;

    public AccessTokenService(String appid, String secret) {
        this.grantType = "client_credential";
        this.appid = appid;
        this.secret = secret;
    }

}
