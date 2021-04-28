package com.limengning.wechat.message;

import com.limengning.wechat.Constants;
import com.limengning.wechat.accesstoken.AccessTokenService;

import io.github.limengning.http.JsonClient;

public class TemplateMessageService {
    private final AccessTokenService accessTokenService;

    public TemplateMessageService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public boolean sendTempalteMessage(TemplateMessage message) {
        String accessToken = accessTokenService.getAccessToken();
        try (JsonClient client = new JsonClient(Constants.BASE_API)) {
            TemplateMessageResult resp = client.post("message/template/send?access_token="+accessToken, TemplateMessageResult.class, message);
            return resp.getErrcode() == 0;
        }
    }
}
