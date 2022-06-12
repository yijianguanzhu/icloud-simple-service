package com.yijianguanzhu.gateway.security.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

/**
 * 权限校验管理器
 *
 * @author yijianguanzhu 2022年06月09日
 */
@Slf4j
public class UserAuthorityReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

	@Override
	public Mono<AuthorizationDecision> check( Mono<Authentication> authentication, AuthorizationContext content ) {
		log.info( "当前版本登录成功后访问权限一律放行" );

		return authentication.filter( Authentication::isAuthenticated )
				.map( hasAuthority -> new AuthorizationDecision( true ) )
				.defaultIfEmpty( new AuthorizationDecision( false ) );
	}
}
