package com.yijianguanzhu.gateway.security.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yijianguanzhu.common.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Slf4j
public class UserAccessDeniedHandler implements ServerAccessDeniedHandler {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private static final String DEFAULT_FAILURE = "无访问权限";

	@Override
	public Mono<Void> handle( ServerWebExchange exchange, AccessDeniedException denied ) {
		log.error( "无访问权限[{}]", denied.getMessage() );
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode( HttpStatus.FORBIDDEN );
		response.getHeaders().setContentType( MediaType.APPLICATION_JSON );
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
