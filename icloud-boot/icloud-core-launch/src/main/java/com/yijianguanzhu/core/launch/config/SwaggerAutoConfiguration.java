package com.yijianguanzhu.core.launch.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.yijianguanzhu.common.constant.TokenConstant;
import com.yijianguanzhu.common.props.SwaggerProperties;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yijianguanzhu 2022年06月09日
 */
@Configuration(proxyBeanMethods = false)
@EnableKnife4j
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnClass(BeanValidatorPluginsConfiguration.class)
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(value = "icloud.swagger.enabled", havingValue = "true", matchIfMissing = false)
public class SwaggerAutoConfiguration {

	private static final String DEFAULT_MAPPING_PATH = "/";
	private static final String DEFAULT_BASE_PATH = "/**";
	private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList( "/error", "/actuator/**" );

	@Bean
	public Docket api( SwaggerProperties swaggerProperties ) {

		List<Predicate<String>> basePath = Lists.newArrayList( PathSelectors.ant( DEFAULT_BASE_PATH ) );
		List<Predicate<String>> excludePath = Lists.newArrayList();
		DEFAULT_EXCLUDE_PATH.forEach( path -> excludePath.add( PathSelectors.ant( path ) ) );
		ApiSelectorBuilder apis = new Docket( DocumentationType.SWAGGER_2 )
				.apiInfo( apiInfo( swaggerProperties ) ).select()
				.apis( RequestHandlerSelectors.withClassAnnotation( Api.class ) )
				.paths( Predicates.and( Predicates.not( Predicates.or( excludePath ) ), Predicates.or( basePath ) ) );

		return apis.build()
				.pathMapping( DEFAULT_MAPPING_PATH )
				.securityContexts( securityContexts() )
				.securitySchemes( securitySchemes() );
	}

	private ApiInfo apiInfo( SwaggerProperties swaggerProperties ) {
		return new ApiInfoBuilder()
				.title( swaggerProperties.getTitle() )
				.description( swaggerProperties.getDescription() )
				.contact( new Contact( swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(),
						swaggerProperties.getContact().getEmail() ) )
				.version( swaggerProperties.getVersion() )
				.build();
	}

	private List<ApiKey> securitySchemes() {
		List<ApiKey> apiKeyList = new ArrayList<ApiKey>();
		apiKeyList.add( new ApiKey( TokenConstant.TOKEN, TokenConstant.TOKEN, "header" ) );
		return apiKeyList;
	}

	private List<SecurityContext> securityContexts() {
		List<SecurityContext> securityContexts = new ArrayList<>();
		securityContexts.add(
				SecurityContext.builder()
						.securityReferences( defaultAuth() )
						.forPaths( PathSelectors.regex( "^(?!auth).*$" ) )
						.build() );
		return securityContexts;
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope( "global", "accessEverything" );
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> securityReferences = new ArrayList<>();
		securityReferences.add( new SecurityReference( TokenConstant.TOKEN, authorizationScopes ) );
		return securityReferences;
	}
}
