package org.meruvian.workshop.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.meruvian.workshop.jaxrs.service.RestNewsService;

@ApplicationPath("/api")
public class Main extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(RestNewsService.class);
		
		return classes;
	}
}
