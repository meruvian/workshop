package org.meruvian.workshop.jaxrs;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.jboss.resteasy.plugins.server.servlet.FilterDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessorServletAware;
import org.meruvian.workshop.jaxrs.config.ApplicationConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

public class Main implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationConfig.class);
		rootContext.register(SpringBeanProcessorServletAware.class);

		// Enable RESTEasy
		Dynamic filter = servletContext.addFilter("Resteasy", FilterDispatcher.class);
		filter.addMappingForUrlPatterns(null, true, "/*");
		
		servletContext.setInitParameter("resteasy.servlet.mapping.prefix", "/api");
		
		servletContext.addListener(new ResteasyBootstrap());
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		registerSwaggerProvider(servletContext);
		registerOauthSecurity(servletContext);
	}
	
	private void registerSwaggerProvider(ServletContext servletContext) {
		servletContext.setInitParameter("resteasy.resources", "com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON");
		servletContext.setInitParameter("resteasy.providers", "com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider, "
				+ "com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider,"
				+ "com.wordnik.swagger.jaxrs.listing.ResourceListingProvider");
	}
	
	private void registerOauthSecurity(ServletContext servletContext) {
		DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");
		filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
		servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");
	}
}
