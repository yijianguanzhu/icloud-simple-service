package com.yijianguanzhu.gateway.swagger.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "icloud.swagger.enabled", havingValue = "true", matchIfMissing = false)
public class SwaggerResourceHandler implements HandlerFunction<ServerResponse> {

	private final SwaggerResourcesProvider swaggerResources;

	@Override
	public Mono<ServerResponse> handle( ServerRequest request ) {
		return ServerResponse.status( HttpStatus.OK )
				.contentType( MediaType.APPLICATION_JSON )
				.body( BodyInserters.fromValue( swaggerResources.get() ) );
	}
}
