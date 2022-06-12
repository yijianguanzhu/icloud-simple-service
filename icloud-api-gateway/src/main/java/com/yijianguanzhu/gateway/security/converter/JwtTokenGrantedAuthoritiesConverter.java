package com.yijianguanzhu.gateway.security.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;

/**
 * token负载权限提取器
 *
 * @author yijianguanzhu 2022年06月09日
 */
public class JwtTokenGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Override
	public Collection<GrantedAuthority> convert( Jwt jwt ) {
		// 现阶段暂时不接入权限获取
		return Collections.EMPTY_LIST;
	}
}
