package me.micopiira.hibernatetest.web;

import me.micopiira.hibernatetest.domain.Customer;
import me.micopiira.hibernatetest.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CustomerController {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@ModelAttribute("bindingResult")
	public BindingResult bindingResult(@ModelAttribute("customer") final Customer customer, BindingResult bindingResult) {
		return bindingResult;
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		customerRepository.deleteById(id);
		redirectAttributes.addFlashAttribute("messages", Collections.singletonList("customer.deleted"));
		return "redirect:/";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("customer") @Valid final Customer customer,
	                     BindingResult bindingResult,
	                     RedirectAttributes redirectAttributes
	) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
			redirectAttributes.addFlashAttribute("customer", customer);
		} else {
			customerRepository.save(customer);
			redirectAttributes.addFlashAttribute("messages", Collections.singletonList("customer.created"));
		}
		return "redirect:/";
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "customers/list";
	}

}
