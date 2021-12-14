package edu.miu.cs.cs544.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.domain.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable(name = "id") Integer customerId) {
		return customerRepository.findById(customerId).get();
	}

	@GetMapping(params = "paged=true")
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
	
	@PostMapping
	public Customer createCountry(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

}
