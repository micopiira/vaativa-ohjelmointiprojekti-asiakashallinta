package me.micopiira.framework;


import com.google.inject.AbstractModule;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(EntityManagerFactory.class).toInstance(Persistence.createEntityManagerFactory("test"));
	}
}