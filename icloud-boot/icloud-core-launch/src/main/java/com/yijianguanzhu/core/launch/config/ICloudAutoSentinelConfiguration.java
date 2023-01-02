package com.yijianguanzhu.core.launch.config;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.yijianguanzhu.core.launch.sentinel.ICloudBlockExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

/**
 * @author yijianguanzhu 2023年01月02日
 */
@AutoConfiguration(before = SentinelFeignAutoConfiguration.class)
@ConditionalOnClass(Servlet.class)
public class ICloudAutoSentinelConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public BlockExceptionHandler blockExceptionHandler() {
		return new ICloudBlockExceptionHandler();
	}
}
