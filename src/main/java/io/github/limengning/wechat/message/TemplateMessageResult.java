package io.github.limengning.wechat.message;

import io.github.limengning.wechat.WechatResponseBase;

public class TemplateMessageResult extends WechatResponseBase {
    public long getMsgid() {
        return msgid;
    }

    public void setMsgid(long msgid) {
        this.msgid = msgid;
    }

    private long msgid;
}
