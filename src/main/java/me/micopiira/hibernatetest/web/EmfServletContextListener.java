package me.micopiira.hibernatetest.web;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EmfServletContextListener implements ServletContextListener {
	private static final String PERSISTENCE_UNIT_NAME = "test";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		sce.getServletContext().setAttribute(
				EntityManagerFactory.class.getName(),
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
		);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
		((EntityManagerFactory) sce.getServletContext().getAttribute(EntityManagerFactory.class.getName())).close();
	}
}
