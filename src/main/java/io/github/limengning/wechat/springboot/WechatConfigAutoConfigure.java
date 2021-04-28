package io.github.limengning.wechat.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.limengning.wechat.WechatConfig;

@Configuration
@ConditionalOnClass(WechatConfigWrapper.class)
@AutoConfigureBefore(WechatConfigAutoConfigure.class)
public class WechatConfigAutoConfigure {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatConfigAutoConfigure.class);

    @Bean()
    @ConditionalOnMissingBean
    public WechatConfig wechatConfig() {
        LOGGER.info("Init WechatConfig");
        return new WechatConfigWrapper();
    }
}
