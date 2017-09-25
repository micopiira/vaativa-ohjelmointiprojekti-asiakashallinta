package me.micopiira.hibernatetest.framework.web;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.micopiira.hibernatetest.domain.CustomerRepository;
import me.micopiira.hibernatetest.jpa.JpaCustomerRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebListener
public class GuiceServletContextListener implements ServletContextListener {

	private Injector injector = Guice.createInjector(new AbstractModule() {
		@Override
		protected void configure() {
			bind(CustomerRepository.class).to(JpaCustomerRepository.class);
			bind(EntityManagerFactory.class).toInstance(Persistence.createEntityManagerFactory("test"));
		}
	});

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute(Injector.class.getName(), injector);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		injector.getInstance(EntityManagerFactory.class).close();
	}

}
