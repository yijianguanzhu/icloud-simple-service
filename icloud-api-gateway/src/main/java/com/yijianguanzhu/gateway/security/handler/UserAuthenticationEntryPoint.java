package com.yijianguanzhu.gateway.security.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yijianguanzhu.common.api.R;
import com.yijianguanzhu.common.constant.TokenConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Slf4j
public class UserAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

	private static final String WWW_AUTHENTICATE_FORMAT = "Bearer realm= \"User Token\"";
	private final static String DEFAULT_FAILURE = "未登录";
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Mono<Void> commence( ServerWebExchange exchange, AuthenticationException e ) {
		log.error( "用户认证失败[{}]，X-Token：{}", e.getMessage(), exchange.getRequest().getHeaders().getFirst( TokenConstant.TOKEN ) );
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode( HttpStatus.UNAUTHORIZED );
		response.getHeaders().setContentType( MediaType.APPLICATION_JSON );
		response.getHeaders().set( HttpHeaders.WWW_AUTHENTICATE, WWW_AUTHENTICATE_FORMAT );
		return response.writeWith( Mono.fromSupplier( () -> {
			DataBufferFactory bufferFactory = response.bufferFactory();
			try {
				return bufferFactory.wrap( objectMapper.writeValueAsBytes( R.fail( DEFAULT_FAILURE ).getBody() ) );
			}
			catch ( JsonProcessingException ex ) {
				// it's should't happened
				return bufferFactory.wrap( new byte[0] );
			}
		} ) );
	}
}
