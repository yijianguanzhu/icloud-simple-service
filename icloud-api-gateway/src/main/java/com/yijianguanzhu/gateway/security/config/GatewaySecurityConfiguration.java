package com.yijianguanzhu.gateway.security.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.yijianguanzhu.common.props.JwtProperties;
import com.yijianguanzhu.feign.client.user.UserFeignClient;
import com.yijianguanzhu.gateway.security.converter.JwtExtractTokenAuthenticationConverter;
import com.yijianguanzhu.gateway.security.converter.JwtTokenGrantedAuthoritiesConverter;
import com.yijianguanzhu.gateway.security.filter.AuthWebFilter;
import com.yijianguanzhu.gateway.security.handler.UserAccessDeniedHandler;
import com.yijianguanzhu.gateway.security.handler.UserAuthenticationEntryPoint;
import com.yijianguanzhu.gateway.security.manager.UserAuthorityReactiveAuthorizationManager;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 网关身份、权限安全配置
 *
 * @author yijianguanzhu 2022年06月09日
 * @see "https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/#reactive-applications"
 * @see "https://github.com/spring-projects/spring-security/issues/9365"
 */
@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfiguration {

	@Autowired
	private JwtProperties jwtProperties;

	/**
	 * 当前版本gateway引入feign bean需要加@Lazy，否则gateway无法在存在服务的情况下注册到nacos
	 */
	@Autowired
	@Lazy
	private UserFeignClient userFeignClient;

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain( ServerHttpSecurity http ) {

		final String[] skipUrls = new String[] {
				"/transfer-service/transfer",
				"/auth/login",
		};

		// Swagger 资源路径
		final String[] skipSwaggerUrls = new String[] {
				"/favicon.ico",
				"/doc.html",
				"/webjars/**",
				"/swagger-resources/**",
				"/*/v2/api-docs" };

		JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
		authenticationConverter.setJwtGrantedAuthoritiesConverter( new JwtTokenGrantedAuthoritiesConverter() );
		ReactiveJwtAuthenticationConverterAdapter jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverterAdapter(
				authenticationConverter );

		// 除跳过的接口外所有接口都需要用户认证，访问权限验证
		ServerWebExchangeMatcher pathMatchers = ServerWebExchangeMatchers
				.pathMatchers( ArrayUtils.addAll( skipUrls, skipSwaggerUrls ) );

		http.securityMatcher( new NegatedServerWebExchangeMatcher( pathMatchers ) )
				.authorizeExchange()
				.pathMatchers( "/**" ).access( new UserAuthorityReactiveAuthorizationManager() )
				.anyExchange().authenticated()
				.and().csrf().disable()
				.addFilterAfter( new AuthWebFilter( userFeignClient ), SecurityWebFiltersOrder.AUTHORIZATION )
				.oauth2ResourceServer()
				.bearerTokenConverter( new JwtExtractTokenAuthenticationConverter() )
				// 身份验证失败处理器
				.authenticationEntryPoint( new UserAuthenticationEntryPoint() )
				// 鉴权失败处理器
				.accessDeniedHandler( new UserAccessDeniedHandler() )
				.jwt()
				.jwtAuthenticationConverter( jwtAuthenticationConverter );

		return http.build();
	}

	@Bean
	@RefreshScope
	public ReactiveJwtDecoder jwtDecoder() {
		byte[] data = jwtProperties.getSecret().getBytes( StandardCharsets.UTF_8 );
		SecretKeySpec secretKey = new SecretKeySpec( data, JWSAlgorithm.HS256.getName() );
		return NimbusReactiveJwtDecoder.withSecretKey( secretKey ).build();
	}
}
