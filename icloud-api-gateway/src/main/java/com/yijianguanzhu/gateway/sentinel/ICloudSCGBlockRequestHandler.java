package com.yijianguanzhu.gateway.sentinel;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

/**
 * sentinel 网关限流降级处理
 * 
 * @author yijianguanzhu 2023年01月02日
 * @see com.alibaba.csp.sentinel.adapter.gateway.sc.callback.DefaultBlockRequestHandler
 */
public class ICloudSCGBlockRequestHandler implements BlockRequestHandler {

	@Override
	public Mono<ServerResponse> handleRequest( ServerWebExchange exchange, Throwable t ) {
		return ServerResponse.status( HttpStatus.OK )
				.contentType( MediaType.APPLICATION_JSON )
				.body( fromValue( "{\"code\":-1, \"msg\":\"当前访问人数过多，请稍后再试\"}" ) );
	}
}
