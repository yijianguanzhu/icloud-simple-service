package com.yijianguanzhu.common.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "icloud.jwt")
public class JwtProperties {

	/**
	 * jwt加解密密钥
	 */
	private String secret = "ad7f4nGdT5tuslz2GpZkh78b5If0m829";

	/**
	 * jwt过期时间
	 */
	private Duration expired;
}
