package org.meruvian.workshop.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.meruvian.workshop.jaxrs.di.ApplicationComponent;
import org.meruvian.workshop.jaxrs.di.DaggerApplicationComponent;
import org.meruvian.workshop.jaxrs.service.RestNewsService;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("/api")
public class Main extends Application {
	private Set<Object> services = new HashSet<Object>();
	
	public Main() {
		configureSwagger();
		configureInjection();
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		// Register swagger configurations
		classes.add(ApiListingResource.class);
		classes.add(SwaggerSerializers.class);
		
		return classes;
	}
	
	@Override
	public Set<Object> getSingletons() {
		// Register services
		return services;
	}

	private void configureSwagger() {
		// Configure swagger
		BeanConfig config = new BeanConfig();
		config.setVersion("0.0.1-SNAPSHOT");
		config.setBasePath("/api");
		config.setResourcePackage("org.meruvian.workshop.jaxrs");
		config.setScan(true);
	}
	
	private void configureInjection() {
		ApplicationComponent component = DaggerApplicationComponent.create();
		services.add(component.inject(new RestNewsService()));
	}
}