package io.github.limengning.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WechatException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatException.class);

    public WechatException(WechatResponseBase resp, String api) {
        super(String.format("%s failed. Error code: %s, error message: %s", api, resp.getErrcode(), resp.getErrmsg()));
        LOGGER.error(super.getMessage());
    }
}
