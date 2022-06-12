package com.yijianguanzhu.feign.client.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Configuration
@ComponentScan("com.yijianguanzhu.feign.client")
@EnableFeignClients("com.yijianguanzhu.feign.client")
public class ICloudAutoFeignConfiguration {
}
