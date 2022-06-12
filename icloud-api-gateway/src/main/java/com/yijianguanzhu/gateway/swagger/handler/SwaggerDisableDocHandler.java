package com.yijianguanzhu.gateway.swagger.handler;

import com.yijianguanzhu.common.api.R;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Component
@ConditionalOnProperty(value = "icloud.swagger.enabled", havingValue = "false", matchIfMissing = true)
public class SwaggerDisableDocHandler implements HandlerFunction<ServerResponse> {

	@Override
	public Mono<ServerResponse> handle( ServerRequest request ) {
		return ServerResponse.status( HttpStatus.FORBIDDEN )
				.contentType( MediaType.APPLICATION_JSON )
				.body( BodyInserters.fromValue( R.fail( "禁止访问" ).getBody() ) );
	}
}
