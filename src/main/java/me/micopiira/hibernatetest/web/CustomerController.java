package me.micopiira.hibernatetest.web;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;
import me.micopiira.hibernatetest.framework.web.Controller;
import me.micopiira.hibernatetest.framework.web.response.ForwardResponse;
import me.micopiira.hibernatetest.framework.web.response.RedirectResponse;
import me.micopiira.hibernatetest.framework.web.response.Response;

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
		final Customer customer = new Customer();
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
