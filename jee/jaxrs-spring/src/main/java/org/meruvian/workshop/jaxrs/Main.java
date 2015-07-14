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
	}
}
