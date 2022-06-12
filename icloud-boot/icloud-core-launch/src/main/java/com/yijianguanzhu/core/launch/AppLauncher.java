package com.yijianguanzhu.core.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yijianguanzhu 2022年06月08日
 */
public class AppLauncher extends SpringBootServletInitializer {

	public static ConfigurableApplicationContext run( Class<?> source, String... args ) {
		return SpringApplication.run( source, args );
	}

	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
		return application.sources( this.getClass() );
	}
}
