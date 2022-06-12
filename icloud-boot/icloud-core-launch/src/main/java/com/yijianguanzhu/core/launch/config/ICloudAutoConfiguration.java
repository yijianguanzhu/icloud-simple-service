package com.yijianguanzhu.core.launch.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import com.yijianguanzhu.common.props.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class ICloudAutoConfiguration {

	/**
	 * 使用NacosRule作为ribbon的负载均衡规则
	 */
	@Bean
	@Scope("prototype")
	public IRule getRibbonRule() {
		return new NacosRule();
	}
}
