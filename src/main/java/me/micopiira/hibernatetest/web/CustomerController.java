package me.micopiira.hibernatetest.web;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@Controller
public class CustomerController {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		customerRepository.deleteById(id);
		redirectAttributes.addAttribute("messages", Collections.singletonList("customer.deleted"));
		return "redirect:/";
	}

	@PostMapping("/create")
	public String create(@RequestParam("firstName") String firstName,
	                       @RequestParam("lastName") String lastName,
	                     RedirectAttributes redirectAttributes) {
		final Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		try {
			customerRepository.save(customer);
			redirectAttributes.addAttribute("messages", Collections.singletonList("customer.created"));
			// getRequest().getSession().removeAttribute("constraintViolations");
		} catch (ConstraintViolationException e) {
			// getRequest().getSession().setAttribute("constraintViolations", e.getConstraintViolations());
		}
		return "redirect:/";
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "customers/list";
	}

}
