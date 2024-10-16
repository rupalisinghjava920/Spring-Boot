package com.springbootrestapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.springbootrestapi.model.Employee;
import com.springbootrestapi.repository.EmployeeRepository;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	

	@Override
	public List<Employee> getEmployees(int pageNumber,int pageSize) {
		Pageable pages= PageRequest.of(pageNumber, pageSize,Direction.DESC,"id");
		return employeeRepository.findAll(pages).getContent();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	

	@Override
	public Employee getSingleEmployee(int id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
		return employee.get();
		}		
		throw new RuntimeException("Employee is not found for the id "+id);
	}

	@Override
	public void deleteEmployee(int id) {
		this.employeeRepository.deleteById(id);

	}

	@Override
	public Employee updateEmployee(Employee employee,int employeeId) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
		return employeeRepository.findByName(name);
	}

	@Override
	public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
		return employeeRepository.findByNameAndLocation(name, location);
	}

	@Override
	public List<Employee> getEmployeesByKeyword(String name) {
		Sort sort= Sort.by(Sort.Direction.ASC,"id");
		return employeeRepository.findByNameContaining(name,sort);
		
	}

	@Override
	public List<Employee> getEmployeesByNameOrLocation(String name, String location) {
		return employeeRepository.getEmployeeByNameAndLocation(name, location);
	}

//	public Optional<Employee> findByEmail(String email){
//	       return EmployeeRepository.findByEmail(email);
//	    }
//
//    public boolean exist(String email){
//	       return EmployeeRepository.existsByEmail(email);
//	    }

	
	
}
