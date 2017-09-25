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
import java.util.function.Function;
import java.util.function.Supplier;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

	private Map<String, Function<HttpServletRequest, Supplier<Response>>> routes = new HashMap<>();

	@Override
	public void init() throws ServletException {
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute(EntityManagerFactory.class.getName());

		Function<HttpServletRequest, CustomerController> customerControllerSupplier = req -> {
			CustomerController controller = new CustomerController(new JpaCustomerRepository(emf));
			controller.setRequest(req);
			return controller;
		};

		routes.put("/", req -> customerControllerSupplier.apply(req)::list);
		routes.put("/delete", req -> customerControllerSupplier.apply(req)::delete);
		routes.put("/create", req -> customerControllerSupplier.apply(req)::create);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		routes.get(req.getRequestURI().substring(req.getContextPath().length()))
				.apply(req).get().execute(req, res);
	}
}
