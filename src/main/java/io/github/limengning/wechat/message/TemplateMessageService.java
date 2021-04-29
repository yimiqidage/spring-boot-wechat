package io.github.limengning.wechat.message;

import io.github.limengning.wechat.Constants;
import io.github.limengning.wechat.WechatException;
import io.github.limengning.wechat.accesstoken.AccessTokenService;
import io.github.limengning.wechat.message.request.TemplateMessage;
import io.github.limengning.http.JsonClient;

public class TemplateMessageService {
    private final AccessTokenService accessTokenService;

    public TemplateMessageService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public void sendTempalteMessage(TemplateMessage message) {
        String accessToken = accessTokenService.getAccessToken();
        try (JsonClient client = new JsonClient(Constants.BASE_API)) {
            TemplateMessageResult resp = client.post("message/template/send?access_token=" + accessToken,
                    TemplateMessageResult.class, message);
            if (resp.getErrcode() != 0) {
                throw new WechatException(resp, "Send tempalte message");
            }
        }
    }
}
