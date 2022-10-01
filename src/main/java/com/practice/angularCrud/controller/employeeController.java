package com.practice.angularCrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.angularCrud.entity.Employee;
import com.practice.angularCrud.exception.employeeNotFoundException;
import com.practice.angularCrud.repository.employeeRepo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employeeapp/")
public class employeeController {

	@Autowired
	private employeeRepo repo;

	@GetMapping("/employees")
	public List<Employee> getEmployeeDetails() {
		return repo.findAll();
	}

	@PostMapping("/employee")
	public Employee addEmployees(@RequestBody Employee employee) {
		return repo.save(employee);
	}

	@GetMapping("/getemployee/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable int id) throws employeeNotFoundException {
		Employee emp = null;
			emp = repo.findById(id).orElseThrow(()->new employeeNotFoundException(id+" employee does not exist"));
		return ResponseEntity.ok(emp);

	}
	
	@PutMapping("/updateemployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee empl) throws employeeNotFoundException {
		Employee emp = null;
		emp = repo.findById(id).orElseThrow(()-> new employeeNotFoundException(id+" employee does not exist"));
		emp.setEmployeeName(empl.getEmployeeName());
		emp.setEmployeeEmail(empl.getEmployeeEmail());
		Employee updatedEmployee = repo.save(emp);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id) throws employeeNotFoundException {
		Employee emp = repo.findById(id).orElseThrow(()-> new employeeNotFoundException(id+" not found"));
		repo.delete(emp);
		Map<String, Boolean> deleted = new HashMap<String, Boolean>();
		deleted.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(deleted);
	}
}
