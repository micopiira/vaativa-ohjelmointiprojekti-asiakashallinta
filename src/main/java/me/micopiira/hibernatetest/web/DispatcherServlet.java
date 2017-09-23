package me.micopiira.hibernatetest.web;

import me.micopiira.hibernatetest.jpa.JpaCustomerRepository;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

	private Map<String, BiFunction<HttpServletRequest, HttpServletResponse, Response>> routes = new HashMap<>();
	private final JpaCustomerRepository customerRepository = new JpaCustomerRepository();

	public DispatcherServlet() {
		CustomerController customerController = new CustomerController(customerRepository);
		routes.put("/", customerController::list);
		routes.put("/delete", customerController::delete);
		routes.put("/create", customerController::create);
	}

	@Override
	public void init() throws ServletException {
		customerRepository.setEntityManagerFactory((EntityManagerFactory) getServletContext().getAttribute(EntityManagerFactory.class.getName()));
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		routes.get(req.getRequestURI()).apply(req, res).execute(req, res);
	}
}
