package wechat;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.limengning.wechat.accesstoken.AccessTokenService;

import org.junit.jupiter.api.Test;

public class AccessTokenServiceTest {
    @Test
    void getAccessToken() {
        AccessTokenService accessTokenService = new AccessTokenService("wxe971e03384a3600a",
                "18d95b6ca46239b46fea8a98978a58a2");
        String accessToken = accessTokenService.getAccessToken();
        assertNotNull(accessToken);
        assertNotEquals("", accessToken);
    }
}
