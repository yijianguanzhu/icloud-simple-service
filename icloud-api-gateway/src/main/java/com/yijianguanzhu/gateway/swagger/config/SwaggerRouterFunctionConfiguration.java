package com.yijianguanzhu.gateway.swagger.config;

import com.yijianguanzhu.gateway.swagger.handler.SwaggerDisableDocHandler;
import com.yijianguanzhu.gateway.swagger.handler.SwaggerResourceHandler;
import com.yijianguanzhu.gateway.swagger.handler.SwaggerUiHandler;
import com.yijianguanzhu.gateway.swagger.props.SwaggerRouteProperties;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * Swagger路由配置信息
 *
 * @author yijianguanzhu 2022年06月09日
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({ SwaggerRouteProperties.class })
@ConditionalOnProperty(value = "icloud.swagger.enabled", havingValue = "true", matchIfMissing = false)
public class SwaggerRouterFunctionConfiguration {

	private final SwaggerResourceHandler swaggerResourceHandler;
	private final SwaggerUiHandler swaggerUiHandler;

	@Bean
	public RouterFunction routerFunction() {
		return RouterFunctions.route( RequestPredicates.GET( "/swagger-resources" )
				.and( RequestPredicates.accept( MediaType.ALL ) ), swaggerResourceHandler )
				.andRoute( RequestPredicates.GET( "/swagger-resources/configuration/ui" )
						.and( RequestPredicates.accept( MediaType.ALL ) ), swaggerUiHandler );
	}

	/**
	 * Swagger聚合文档UI资源禁用
	 */
	@Configuration
	@ConditionalOnProperty(value = "icloud.swagger.enabled", havingValue = "false", matchIfMissing = true)
	@RequiredArgsConstructor
	static class SwaggerDocRouterFunctionConfiguration {

		private final SwaggerDisableDocHandler swaggerDocHandler;

		@Bean
		public RouterFunction routerFunction() {
			return RouterFunctions.route( RequestPredicates.GET( "/doc.html" )
					.and( RequestPredicates.accept( MediaType.ALL ) ), swaggerDocHandler );
		}
	}
}
