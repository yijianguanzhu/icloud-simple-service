package com.yijianguanzhu.gateway.security.converter;

import com.yijianguanzhu.common.constant.TokenConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 提取token器
 *
 * @author yijianguanzhu 2022年06月09日
 * @see org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter
 */
public class JwtExtractTokenAuthenticationConverter implements ServerAuthenticationConverter {

	private final static String DEFAULT_FAILURE = "未设置X-Token值";

	@Override
	public Mono<Authentication> convert( ServerWebExchange exchange ) {

		return Mono.justOrEmpty( token( exchange.getRequest() ) )
				.map( token -> {
					if ( token.isEmpty() ) {
						throw new OAuth2AuthenticationException( invalidTokenError() );
					}
					return new BearerTokenAuthenticationToken( token );
				} );
	}

	// 从header中获取token，获取不到就从query string parameter中获取。
	private String token( ServerHttpRequest request ) {
		String authorizationHeaderToken = resolveFromAuthorizationHeader( request.getHeaders() );
		if ( StringUtils.hasText( authorizationHeaderToken ) ) {
			return authorizationHeaderToken;
		}
		return request.getQueryParams().getFirst( TokenConstant.TOKEN );
	}

	// 简单地从请求头X-Token中获取token
	private static String resolveFromAuthorizationHeader( HttpHeaders headers ) {
		return headers.getFirst( TokenConstant.TOKEN );
	}

	private static OAuth2Error invalidTokenError() {
		return new OAuth2Error( DEFAULT_FAILURE, DEFAULT_FAILURE, null );
	}
}
