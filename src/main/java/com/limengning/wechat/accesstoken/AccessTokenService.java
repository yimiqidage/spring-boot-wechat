package com.limengning.wechat.accesstoken;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

import io.github.limengning.http.JsonClient;
import com.limengning.wechat.Constants;

public class AccessTokenService {
    private static final int AHEAD_TIME = 300;
    private static final Map<String, String> ACCESS_TOKEN = new HashMap<>(0);
    private static Timer timer = null;
    private final String grantType;
    private final String appid;
    private final String secret;

    public AccessTokenService(String appid, String secret) {
        this.grantType = "client_credential";
        this.appid = appid;
        this.secret = secret;
        ACCESS_TOKEN.put(appid, null);
    }

    private synchronized void setAccessToken(String token) {
        ACCESS_TOKEN.put(appid, token);
    }

    public synchronized String getAccessToken() {
        String accessToken = ACCESS_TOKEN.get(appid);
        if (StringUtils.isEmpty(accessToken)) {
            loadAccessTokenFromWechat();
            accessToken = ACCESS_TOKEN.get(appid);
        }
        return accessToken;
    }

    public synchronized String reloadAccessToken() {
        setAccessToken(null);
        return getAccessToken();
    }

    private synchronized void loadAccessTokenFromWechat() {
        Map<String, String> queryParameters = new HashMap<>(3);
        queryParameters.put("grant_type", grantType);
        queryParameters.put("appid", appid);
        queryParameters.put("secret", secret);
        try (JsonClient client = new JsonClient(Constants.BASE_API)) {
            AccessTokenResponse resp = client.get("token", AccessTokenResponse.class, queryParameters);
            if (timer != null) {
                timer.cancel();
            }
            timer = new Timer();
            if (resp.getErrorcode() == 0) {
                timer.schedule(new TimerTask() {
                    public void run() {
                        loadAccessTokenFromWechat();
                    }
                }, (resp.getExpiresIn() - AHEAD_TIME));
                setAccessToken(resp.getAccessToken());
            } else {
                timer.schedule(new TimerTask() {
                    public void run() {
                        loadAccessTokenFromWechat();
                    }
                }, AHEAD_TIME);
                setAccessToken(null);
            }
        }
    }

}
