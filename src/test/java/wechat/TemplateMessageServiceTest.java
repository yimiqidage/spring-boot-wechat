package wechat;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import io.github.limengning.wechat.accesstoken.AccessTokenService;
import io.github.limengning.wechat.message.TemplateMessage;
import io.github.limengning.wechat.message.TemplateMessageData;
import io.github.limengning.wechat.message.TemplateMessageService;

import org.junit.jupiter.api.Test;

public class TemplateMessageServiceTest {
    @Test
    public void sendTempalteMessage() {
        AccessTokenService accessTokenService = new AccessTokenService("wxe971e03384a3600a",
                "18d95b6ca46239b46fea8a98978a58a2");
        TemplateMessageService templateMessageService = new TemplateMessageService(accessTokenService);
        TemplateMessage message = new TemplateMessage();
        message.setTouser("ooSjn6aQzjeoCG2jVnr5iLNntH_I");
        message.setTemplate_id("XSREn90rhGWu_FOueKfZmT5lgcwOK-YPvpfLHhZZDJY");
        Map<String, TemplateMessageData> data = new HashMap<>(1);
        data.put("name", new TemplateMessageData("limengning"));
        message.setData(data);
        message.setUrl("https://www.baidu.com");
        boolean result = templateMessageService.sendTempalteMessage(message);
        assertTrue(result);
    }
}
