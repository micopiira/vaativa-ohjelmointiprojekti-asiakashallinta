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

	@Override
	public void init() throws ServletException {
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute(EntityManagerFactory.class.getName());
		CustomerController customerController = new CustomerController(new JpaCustomerRepository(emf));
		routes.put("/", customerController::list);
		routes.put("/delete", customerController::delete);
		routes.put("/create", customerController::create);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		routes.get(req.getRequestURI().substring(req.getContextPath().length())).apply(req, res).execute(req, res);
	}
}
