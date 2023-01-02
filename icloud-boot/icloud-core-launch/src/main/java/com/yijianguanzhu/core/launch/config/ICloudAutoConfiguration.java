package com.yijianguanzhu.core.launch.config;

import com.yijianguanzhu.common.props.JwtProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@AutoConfiguration
@EnableConfigurationProperties(JwtProperties.class)
public class ICloudAutoConfiguration {
}
