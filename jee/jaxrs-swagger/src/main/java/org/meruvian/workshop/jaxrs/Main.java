package org.meruvian.workshop.jaxrs;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.meruvian.workshop.jaxrs.service.RestNewsService;

@ApplicationPath("/api")
public class Main extends Application {
	public Main() {
		// Configure swagger
		BeanConfig config = new BeanConfig();
		config.setVersion("0.0.1-SNAPSHOT");
		config.setBasePath("/api");
        config.setResourcePackage("org.meruvian.workshop.jaxrs");
		config.setScan(true);
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		// Register swagger configurations
		classes.add(ApiListingResource.class);
		classes.add(SwaggerSerializers.class);
		
		// Register services
		classes.add(RestNewsService.class);

		return classes;
	}
}
