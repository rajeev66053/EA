package edu.miu.cs.cs544.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.repository.CountryRepository;

@RestController
@RequestMapping("/countries")
public class CountryController {
	
	@Autowired
	private CountryRepository repository;
	
	@GetMapping
	public List<Country> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Country findById(@PathVariable(name="id") String countryId){
		return repository.findById(countryId).get();
	}

}
