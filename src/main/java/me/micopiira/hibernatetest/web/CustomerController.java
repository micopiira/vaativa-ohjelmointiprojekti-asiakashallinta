package me.micopiira.hibernatetest.web;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;

public class CustomerController extends Controller {

	private final CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Response delete() {
		customerRepository.deleteById(Long.valueOf(getRequiredParameter("id")));
		addMessage("customer.deleted");
		return new RedirectResponse("/");
	}

	public Response create() {
		Customer customer = new Customer();
		customer.setName(getRequiredParameter("name"));
		customerRepository.save(customer);
		addMessage("customer.created");
		return new RedirectResponse("/");
	}

	public Response list() {
		getRequest().setAttribute("customers", customerRepository.findAll());
		return new ForwardResponse("list.jsp");
	}

}
