package com.springbootrestapi.service;

import java.util.List;
import com.springbootrestapi.model.Employee;

public interface EmployeeService {

	List<Employee> getEmployees(int pageNumber, int pageSize);
	
	Employee saveEmployee(Employee employee);
	
	Employee getSingleEmployee(int id);


	void deleteEmployee(int employeeId);

	Employee updateEmployee(Employee employee, int employeeId);
	
	List<Employee> getEmployeesByName(String name);
	
	List<Employee> getEmployeesByNameAndLocation(String name,String location);
	
	List<Employee> getEmployeesByKeyword(String name);

	List<Employee> getEmployeesByNameOrLocation(String name,String location);

	
}
