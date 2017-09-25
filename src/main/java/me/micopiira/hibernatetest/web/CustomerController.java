package me.micopiira.hibernatetest.web;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;

public class CustomerController {

	private final CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Response delete(HttpServletRequest req, HttpServletResponse res) {
		customerRepository.deleteById(Long.valueOf(getRequiredParameter(req, "id")));
		req.getSession().setAttribute("messages", Collections.singletonList("customer.deleted"));
		return new RedirectResponse("/");
	}

	public Response create(HttpServletRequest req, HttpServletResponse res) {
		Customer customer = new Customer();
		customer.setName(getRequiredParameter(req, "name"));
		customerRepository.save(customer);
		req.getSession().setAttribute("messages", Collections.singletonList("customer.created"));
		return new RedirectResponse("/");
	}

	public Response list(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("customers", customerRepository.findAll());
		return new ForwardResponse("list.jsp");
	}

	private String getRequiredParameter(HttpServletRequest req, String s) {
		return Optional.ofNullable(req.getParameter(s)).orElseThrow(() -> new RuntimeException("Missing required paramter '" + s + "'"));
	}
}
