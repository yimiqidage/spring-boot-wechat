package io.github.limengning.wechat.accesstoken;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.limengning.http.JsonClient;
import io.github.limengning.wechat.Constants;
import io.github.limengning.wechat.WechatConfig;

public class AccessTokenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenService.class);
    private static final int AHEAD_TIME = 300;
    private static final Map<String, String> ACCESS_TOKEN = new HashMap<>(0);
    private static Timer timer = null;
    private final String grantType;
    private final String appid;
    private final String secret;

    public AccessTokenService(WechatConfig wechatConfig) {
        this.grantType = "client_credential";
        this.appid = wechatConfig.getAppid();
        this.secret = wechatConfig.getSecret();
        ACCESS_TOKEN.put(appid, null);
    }

    private synchronized void setAccessToken(String token) {
        ACCESS_TOKEN.put(appid, token);
    }

    public synchronized String getAccessToken() {
        String accessToken = ACCESS_TOKEN.get(appid);
        if (StringUtils.isEmpty(accessToken)) {
            LOGGER.debug("Access token is empty, get from wechat api.");
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
            if (resp.getErrcode() == 0) {
                timer.schedule(new TimerTask() {
                    public void run() {
                        loadAccessTokenFromWechat();
                    }
                }, (resp.getExpiresIn() - AHEAD_TIME));
                setAccessToken(resp.getAccessToken());
                String message = String.format("Get access token from wechat api success. Access token: %s.",
                        resp.getAccessToken());
                LOGGER.debug(message);
            } else {
                String message = String.format(
                        "Get access token from wechat api failed, retry after %s seconds. Error code: %s, error message: %s.",
                        AHEAD_TIME, resp.getErrcode(), resp.getErrmsg());
                LOGGER.error(message);
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
