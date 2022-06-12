package com.yijianguanzhu.gateway.security.filter;

import brave.baggage.BaggageField;
import com.yijianguanzhu.common.constant.JwtConstant;
import com.yijianguanzhu.common.constant.TokenConstant;
import com.yijianguanzhu.common.result.UserResult;
import com.yijianguanzhu.common.utils.ProtostuffUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Map;

/**
 * 用户认证成功后，将用户信息设置到分布式链路追踪系统中。 {@link brave.baggage.BaggagePropagation}
 *
 * @author yijianguanzhu 2022年06月09日
 */
@Slf4j
public class AuthWebFilter implements WebFilter {

	@Override
	public Mono<Void> filter( ServerWebExchange exchange, WebFilterChain chain ) {

		return ReactiveSecurityContextHolder.getContext()
				.flatMap( context -> Mono.justOrEmpty( context.getAuthentication() ) )
				.switchIfEmpty( chain.filter( exchange ).then( Mono.empty() ) )
				.map( auth -> {
					log.info( "用户认证结束." );
					UserResult userInfo = getUserInfo( ( Jwt ) auth.getPrincipal() );
					BaggageField baggageField = BaggageField.create( TokenConstant.USER_INFO );
					baggageField.updateValue( Base64.getEncoder().encodeToString( ProtostuffUtil.serialize( userInfo ) ) );
					return exchange;
				} )
				.flatMap( chain::filter );
	}

	// 获取用户信息
	private UserResult getUserInfo( Jwt jwt ) {
		Map<String, Object> claims = jwt.getClaims();
		return UserResult.builder()
				.userId( ( Long ) claims.get( JwtConstant.JWT_USER_ID ) )
				.username( ( String ) claims.get( JwtConstant.JWT_USER_NAME ) )
				.build();
	}
}
