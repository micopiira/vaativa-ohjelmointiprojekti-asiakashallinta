package me.micopiira.framework.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import me.micopiira.framework.HibernateModule;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebListener
public class GuiceServletContextListener implements ServletContextListener {

	private Injector injector;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		injector = Guice.createInjector(new HibernateModule());
		sce.getServletContext().setAttribute(Injector.class.getName(), injector);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Optional.ofNullable(injector)
				.map(i -> i.getInstance(EntityManagerFactory.class))
				.ifPresent(EntityManagerFactory::close);
	}

}
