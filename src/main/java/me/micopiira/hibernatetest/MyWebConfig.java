package me.micopiira.hibernatetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan
@PropertySource("classpath:application.properties")
public class MyWebConfig implements WebMvcConfigurer {

	private final Environment environment;

	@Autowired
	public MyWebConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	EntityManagerFactory entityManagerFactory() {
		Map<String, String> configOverrides = new HashMap<>();
		configOverrides.put("javax.persistence.jdbc.url", environment.getProperty("JDBC_DATABASE_URL"));
		configOverrides.put("javax.persistence.jdbc.user", environment.getProperty("JDBC_DATABASE_USERNAME"));
		configOverrides.put("javax.persistence.jdbc.password", environment.getProperty("JDBC_DATABASE_PASSWORD"));
		configOverrides.put("javax.persistence.jdbc.driver", environment.getProperty("JDBC_DATABASE_DRIVER"));
		return Persistence.createEntityManagerFactory("test", configOverrides);
	}

	@Bean
	UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
