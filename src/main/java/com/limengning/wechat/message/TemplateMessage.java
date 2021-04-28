package com.limengning.wechat.message;

import java.util.Map;

public class TemplateMessage {
    private String touser;
    private String template_id;
    public String getTouser() {
        return touser;
    }
    public void setTouser(String touser) {
        this.touser = touser;
    }
    public String getTemplate_id() {
        return template_id;
    }
    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public TemplateMessageMinprogram getMiniprogram() {
        return miniprogram;
    }
    public void setMiniprogram(TemplateMessageMinprogram miniprogram) {
        this.miniprogram = miniprogram;
    }
    public Map<String, TemplateMessageData> getData() {
        return data;
    }
    public void setData(Map<String, TemplateMessageData> data) {
        this.data = data;
    }
    private String url;
    private TemplateMessageMinprogram miniprogram;
    private Map<String,  TemplateMessageData> data;
}
