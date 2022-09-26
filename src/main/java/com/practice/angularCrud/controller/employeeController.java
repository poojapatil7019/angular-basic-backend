package com.practice.angularCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.angularCrud.entity.Employee;
import com.practice.angularCrud.repository.employeeRepo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee/")
public class employeeController {

	@Autowired
	private employeeRepo repo;

	@GetMapping("/employees")
	public List<Employee> getEmployeeDetails() {
		return repo.findAll();
	}
	
	@PostMapping("/employees")
	public Employee addEmployees(@RequestBody Employee employee) {
		return repo.save(employee);
	}

}
