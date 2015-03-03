package org.meruvian.workshop.jaxrs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wordnik.swagger.jaxrs.config.BeanConfig;

@Configuration
public class SwaggerConfig {
	@Bean
	public BeanConfig beanConfig() {
		BeanConfig config = new BeanConfig();
		config.setVersion("0.0.1-SNAPSHOT");
		config.setBasePath("/api");
        config.setResourcePackage("org.meruvian.workshop.jaxrs");
		config.setScan(true);
		
		return config;
	}
}
