package me.micopiira.hibernatetest.web;

import me.micopiira.hibernatetest.domain.CustomerRepository;
import me.micopiira.hibernatetest.jpa.JpaCustomerRepository;
import me.micopiira.hibernatetest.web.CustomerController;
import me.micopiira.hibernatetest.framework.web.response.Response;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

	private final Map<String, Function<HttpServletRequest, Supplier<Response>>> routes = new HashMap<>();
	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");

	public DispatcherServlet() {
		final CustomerRepository customerRepository = new JpaCustomerRepository(entityManagerFactory);
		final Function<HttpServletRequest, CustomerController> customerControllerSupplier = req -> {
			final CustomerController controller = new CustomerController(customerRepository);
			controller.setRequest(req);
			return controller;
		};
		routes.put("/", req -> customerControllerSupplier.apply(req)::list);
		routes.put("/delete", req -> customerControllerSupplier.apply(req)::delete);
		routes.put("/create", req -> customerControllerSupplier.apply(req)::create);
	}

	@Override
	public void destroy() {
		entityManagerFactory.close();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		routes.get(req.getRequestURI().substring(req.getContextPath().length())).apply(req).get().execute(req, res);
	}
}
