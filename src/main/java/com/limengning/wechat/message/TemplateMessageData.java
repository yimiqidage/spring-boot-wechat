package com.limengning.wechat.message;

public class TemplateMessageData {
    public TemplateMessageData(String value) {
        this(value, null);
    }
    public TemplateMessageData(String value, String color) {
        this.value = value;
        this.color = color;
    }
    private String value;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    private String color;
}
