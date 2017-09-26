package me.micopiira.hibernatetest.web;

import com.google.inject.Injector;
import me.micopiira.framework.web.response.Response;

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

	public DispatcherServlet() {
		final Function<HttpServletRequest, CustomerController> customerControllerSupplier = req -> {
			final Injector injector = (Injector) getServletContext().getAttribute(Injector.class.getName());
			final CustomerController controller = injector.getInstance(CustomerController.class);
			controller.setRequest(req);
			return controller;
		};
		routes.put("/", req -> customerControllerSupplier.apply(req)::list);
		routes.put("/delete", req -> customerControllerSupplier.apply(req)::delete);
		routes.put("/create", req -> customerControllerSupplier.apply(req)::create);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		routes.get(req.getRequestURI().substring(req.getContextPath().length())).apply(req).get().execute(req, res);
	}
}
