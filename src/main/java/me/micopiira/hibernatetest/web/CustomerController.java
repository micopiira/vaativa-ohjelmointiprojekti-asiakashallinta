package me.micopiira.hibernatetest.web;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;
import me.micopiira.framework.web.Controller;
import me.micopiira.framework.web.response.ForwardResponse;
import me.micopiira.framework.web.response.RedirectResponse;
import me.micopiira.framework.web.response.Response;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

public class CustomerController extends Controller {

	private final CustomerRepository customerRepository;

	@Inject
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
		customer.setFirstName(getRequiredParameter("firstName"));
		customer.setLastName(getRequiredParameter("lastName"));
		try {
			customerRepository.save(customer);
			addMessage("customer.created");
			getRequest().getSession().removeAttribute("constraintViolations");
		} catch (ConstraintViolationException e) {
			getRequest().getSession().setAttribute("constraintViolations", e.getConstraintViolations());
		}
		return new RedirectResponse("/");
	}

	public Response list() {
		getRequest().setAttribute("customers", customerRepository.findAll());
		return new ForwardResponse("/customers/list.jsp");
	}

}
