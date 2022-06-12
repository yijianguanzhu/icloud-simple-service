package com.yijianguanzhu.gateway.swagger.provider;

import com.google.common.collect.Lists;
import com.yijianguanzhu.gateway.swagger.props.SwaggerRouteProperties;
import com.yijianguanzhu.gateway.swagger.props.SwaggerRouteResource;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;

/**
 * Swagger聚合接口文档注册
 *
 * @author yijianguanzhu 2022年06月09日
 */
@Primary
@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "icloud.swagger.enabled", havingValue = "true", matchIfMissing = false)
public class SwaggerProvider implements SwaggerResourcesProvider {

	// Swagger接口访问地址
	private static final String API_URI = "/v2/api-docs";

	private SwaggerRouteProperties swaggerRouteProperties;

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = Lists.newArrayList();
		List<SwaggerRouteResource> routeResources = swaggerRouteProperties.getResources();
		routeResources.forEach( routeResource -> resources.add( swaggerResource( routeResource ) ) );
		return resources;
	}

	private SwaggerResource swaggerResource( SwaggerRouteResource routeResource ) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName( routeResource.getName() );
		swaggerResource.setUrl( routeResource.getUrl().concat( API_URI ) );
		swaggerResource.setSwaggerVersion( routeResource.getVersion() );
		return swaggerResource;
	}
}
