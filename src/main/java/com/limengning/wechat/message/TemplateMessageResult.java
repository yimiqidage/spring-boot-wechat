package com.limengning.wechat.message;

public class TemplateMessageResult {
    private int errcode;
    private String errmsg;
    public int getErrcode() {
        return errcode;
    }
    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public long getMsgid() {
        return msgid;
    }
    public void setMsgid(long msgid) {
        this.msgid = msgid;
    }
    private long msgid;
}
