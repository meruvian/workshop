package org.meruvian.workshop.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.meruvian.workshop.jaxrs.service.RestNewsService;

import com.wordnik.swagger.jaxrs.config.BeanConfig;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResource;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;

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
		classes.add(ApiDeclarationProvider.class);
		classes.add(ApiListingResourceJSON.class);
		classes.add(ResourceListingProvider.class);
		
		// Register services
		classes.add(RestNewsService.class);
		
		return classes;
	}
}
