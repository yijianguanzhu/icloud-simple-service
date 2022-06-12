package com.yijianguanzhu.gateway.swagger.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Data
@ConfigurationProperties("icloud.swagger.document")
public class SwaggerRouteProperties {

	private final List<SwaggerRouteResource> resources = new ArrayList<>();
}
