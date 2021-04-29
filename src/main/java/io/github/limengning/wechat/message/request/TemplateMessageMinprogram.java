package io.github.limengning.wechat.message.request;

public class TemplateMessageMinprogram {
    public TemplateMessageMinprogram(String appid, String pagepath) {
        this.appid = appid;
        this.pagepath = pagepath;
    }

    private String appid;
    private String pagepath;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}
