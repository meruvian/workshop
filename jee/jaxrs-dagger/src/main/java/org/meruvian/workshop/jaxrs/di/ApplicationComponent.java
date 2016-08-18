package org.meruvian.workshop.jaxrs.di;

import javax.inject.Singleton;

import org.meruvian.workshop.jaxrs.di.module.RepositoryModule;
import org.meruvian.workshop.jaxrs.service.RestNewsService;

import dagger.Component;

@Singleton
@Component(modules = { RepositoryModule.class })
public interface ApplicationComponent {
	RestNewsService inject(RestNewsService service);
}
