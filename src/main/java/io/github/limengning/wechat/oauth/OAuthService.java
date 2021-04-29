package io.github.limengning.wechat.oauth;

import java.util.HashMap;
import java.util.Map;

import io.github.limengning.http.JsonClient;
import io.github.limengning.wechat.WechatException;
import io.github.limengning.wechat.WechatConfig;

public class OAuthService {
    private final WechatConfig wechatConfig;

    public OAuthService(WechatConfig wechatConfig) {
        this.wechatConfig = wechatConfig;
    }

    public String getOpenId(String code) throws WechatException {
        return getAccessToken(code).getOpenid();
    }

    public WechatUserInfo getUserInfo(String code) throws WechatException {
        AccessToken accessToken = getAccessToken(code);
        return getUserInfo(accessToken);
    }

    private AccessToken getAccessToken(String code) throws WechatException {
        try (JsonClient client = new JsonClient()) {
            Map<String, String> queryParameters = new HashMap<>(4);
            queryParameters.put("appid", wechatConfig.getAppid());
            queryParameters.put("secret", wechatConfig.getSecret());
            queryParameters.put("code", code);
            queryParameters.put("grant_type", "authorization_code");

            AccessToken resp = client.get("https://api.weixin.qq.com/sns/oauth2/access_token", AccessToken.class,
                    queryParameters);
            if (resp.getErrcode() != 0) {
                throw new WechatException(resp, "Get oauth access token");
            }
            return resp;

        }
    }

    private WechatUserInfo getUserInfo(AccessToken accessToken) {
        try (JsonClient client = new JsonClient()) {
            Map<String, String> queryParameters = new HashMap<>(4);
            queryParameters.put("access_token", accessToken.getAccess_token());
            queryParameters.put("openid", accessToken.getOpenid());
            queryParameters.put("lang", "zh_CN");

            WechatUserInfo resp = client.get(" https://api.weixin.qq.com/sns/userinfo", WechatUserInfo.class,
                    queryParameters);
            if (resp.getErrcode() != 0) {
                throw new WechatException(resp, "Get user info");
            }
            return resp;

        }
    }
}
