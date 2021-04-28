package wechat;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.limengning.wechat.accesstoken.AccessTokenService;
import io.github.limengning.wechat.springboot.WechatConfigWrapper;

import org.junit.jupiter.api.Test;

public class AccessTokenServiceTest {
    @Test
    void getAccessToken() {
        WechatConfigWrapper wechatConfig = new WechatConfigWrapper();
        wechatConfig.setAppid("wxe971e03384a3600a");
        wechatConfig.setSecret("18d95b6ca46239b46fea8a98978a58a2");
        AccessTokenService accessTokenService = new AccessTokenService(wechatConfig);
        String accessToken = accessTokenService.getAccessToken();
        assertNotNull(accessToken);
        assertNotEquals("", accessToken);
    }
}
