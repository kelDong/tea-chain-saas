package com.teachain.applet.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayConfig {

    private String appId;
    private String mchId;
    private String apiKey;
    private String notifyUrl;
}
