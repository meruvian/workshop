package org.meruvian.workshop.jaxrs.di.module;

import javax.inject.Singleton;

import org.meruvian.workshop.jaxrs.repository.NewsRepository;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class RepositoryModule {
	@Provides
	@Singleton
	public NewsRepository provideNewsRepository() {
		return new NewsRepository();
	}
}
