package me.micopiira.hibernatetest.web;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerController {

	private final CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Response delete(HttpServletRequest request, HttpServletResponse response) {
		customerRepository.deleteById(Long.valueOf(request.getParameter("id")));
		return new RedirectResponse("/");
	}

	public Response create(HttpServletRequest req, HttpServletResponse res) {
		Customer customer = new Customer();
		customer.setName(req.getParameter("name"));
		customerRepository.save(customer);
		return new RedirectResponse("/");
	}

	public Response list(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("customers", customerRepository.findAll());
		return new ForwardResponse("list.jsp");
	}
}
