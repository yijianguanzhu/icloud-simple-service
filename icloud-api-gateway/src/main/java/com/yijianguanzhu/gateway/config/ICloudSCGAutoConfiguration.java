package com.yijianguanzhu.gateway.config;

import com.alibaba.cloud.sentinel.gateway.scg.SentinelSCGAutoConfiguration;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.yijianguanzhu.gateway.sentinel.ICloudSCGBlockRequestHandler;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关统一限流规则配置
 * 
 * @author yijianguanzhu 2023年01月02日
 * @see com.alibaba.cloud.sentinel.gateway.scg.SentinelSCGAutoConfiguration
 */
@Configuration
@AutoConfigureBefore(SentinelSCGAutoConfiguration.class)
public class ICloudSCGAutoConfiguration {

	@Bean
	public BlockRequestHandler blockRequestHandler() {
		return new ICloudSCGBlockRequestHandler();
	}
}
