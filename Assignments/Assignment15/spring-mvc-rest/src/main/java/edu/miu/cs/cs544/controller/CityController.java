package edu.miu.cs.cs544.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.cs.cs544.domain.City;
import edu.miu.cs.cs544.repository.CityRepository;

@RestController
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private CityRepository repository;
	
	@GetMapping
	public List<City> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public City findById(@PathVariable(name="id") Long cityId){
		return repository.findById(cityId).get();
	}

}
