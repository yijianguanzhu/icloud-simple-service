package com.yijianguanzhu.common.utils;

import com.google.common.collect.ImmutableMap;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.util.StandardCharset;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.yijianguanzhu.common.constant.JwtConstant;
import com.yijianguanzhu.common.props.JwtProperties;
import com.yijianguanzhu.common.result.UserResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Component
@Slf4j
public class JwtUtil {

	private static JwtProperties jwtProperties;

	/**
	 * 生成jwt
	 */
	public static String token( UserResult user, Long expireMillis ) {
		try {
			long nowMillis = System.currentTimeMillis();
			// 负载
			JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder()
					.notBeforeTime( new Date( nowMillis ) )
					.expirationTime( new Date(
							nowMillis + ( Objects.isNull( expireMillis ) ? jwtProperties.getExpired().toMillis() : expireMillis ) ) );
			Map<String, Object> claims = new HashMap<>( ImmutableMap.<String, Object>builder()
					.put( JwtConstant.JWT_SUB, user.getUsername() )
					.put( JwtConstant.JWT_USER_NAME, user.getUsername() )
					.put( JwtConstant.JWT_USER_ID, user.getUserId() )
					.put( JwtConstant.JWT_CREATED, LocalDateTime.now() )
					.build() );
			claims.forEach( builder::claim );
			JWTClaimsSet claimsSet = builder.build();
			JWSHeader jwsHeader = new JWSHeader.Builder( JWSAlgorithm.HS256 ).type( JOSEObjectType.JWT ).build();
			// 建立签名
			SignedJWT jwt = new SignedJWT( jwsHeader, claimsSet );
			MACSigner macSigner = new MACSigner( jwtProperties.getSecret().getBytes( StandardCharset.UTF_8 ) );
			jwt.sign( macSigner );
			// 生成token
			return jwt.serialize();
		}
		catch ( JOSEException ex ) {
			// it's should't happened
			log.error( "生成token失败：", ex );
			throw new RuntimeException( ex.getMessage(), ex );
		}
	}

	/**
	 * 生成jwt
	 */
	public static String token( UserResult user ) {
		return token( user, null );
	}

	/**
	 * 创建bean时，调用此方法设置静态变量 jwtProperties
	 */
	@Autowired
	public void setJwtProperties( JwtProperties jwtProps ) {
		jwtProperties = jwtProps;
	}
}
