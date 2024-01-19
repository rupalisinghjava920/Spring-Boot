package com.springbootrestapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springbootrestapi.model.Employee;
import com.springbootrestapi.repository.EmployeeRepository;
import com.springbootrestapi.service.EmployeeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository ;
	
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
		return new ResponseEntity<List<Employee>>(employeeService.getEmployees(pageNumber,pageSize),HttpStatus.OK);
	}
	
//	@PostMapping("/save")
//	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
//		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.OK);
//	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody Employee employee) {
		if(employeeRepository.existsByEmail(employee.getEmail())) {
			return ResponseEntity.badRequest().body("Email address already in use");
		}
		employeeRepository.save(employee);
		return ResponseEntity.ok("Employee save successfully");
	}


	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		return new ResponseEntity<>(this.employeeService.getSingleEmployee(id),HttpStatus.OK);
	}
	
	@Transactional
	@DeleteMapping("/employees")
	public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable Long id) {
		return new ResponseEntity<Employee>(this.employeeService.saveEmployee(employee),HttpStatus.OK);
	}
	
	@GetMapping("/employees/filterByName")
	public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name){
		return new ResponseEntity<List<Employee>>(this.employeeService.getEmployeesByName(name),HttpStatus.OK);
	}
	
	@GetMapping("/employees/filterByNameAndLocation")
	public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name,@RequestParam String location){
		return new ResponseEntity<List<Employee>>(this.employeeService.getEmployeesByNameAndLocation(name, location),HttpStatus.OK);
	}
	
	@GetMapping("/employees/filterByKeyword")
	public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name){
		return new ResponseEntity<List<Employee>>(this.employeeService.getEmployeesByKeyword(name),HttpStatus.OK);
	}
	
	@GetMapping("/employees/{name}/{location}")
	public ResponseEntity<List<Employee>> getEmployeesByNameOrLocation(@PathVariable String name,@PathVariable String location){
		return new ResponseEntity<List<Employee>>(this.employeeService.getEmployeesByNameOrLocation(name, location),HttpStatus.OK);
	}
	
	
}

