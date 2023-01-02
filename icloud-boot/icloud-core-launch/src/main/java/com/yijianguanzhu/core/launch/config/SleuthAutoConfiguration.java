package com.yijianguanzhu.core.launch.config;

import brave.baggage.BaggageFields;
import brave.baggage.CorrelationScopeConfig;
import brave.baggage.CorrelationScopeCustomizer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * parentId添加到MDC中
 * 
 * @author yijianguanzhu 2023年01月02日
 * @see org.springframework.cloud.sleuth.autoconfig.brave.BraveBaggageConfiguration#correlationScopeDecorator
 */
@AutoConfiguration
public class SleuthAutoConfiguration {

	@Bean
	public CorrelationScopeCustomizer createCorrelationScopeCustomizer() {
		return builder -> builder
                .add( CorrelationScopeConfig.SingleCorrelationField.create( BaggageFields.PARENT_ID ) )
				.add( CorrelationScopeConfig.SingleCorrelationField.create( BaggageFields.SAMPLED ) );
	}
}
