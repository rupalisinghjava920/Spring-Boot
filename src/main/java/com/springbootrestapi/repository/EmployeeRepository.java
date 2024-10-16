package com.springbootrestapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springbootrestapi.model.Employee;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer>{

	
	Employee save(Employee employee);
	
	Optional<Employee> findById(int id);

	void deleteById(int id);

	List<Employee> findByName(String name); 
	
	//Select * from table where name="Seete" And location="India"
	List<Employee> findByNameAndLocation(String name,String location);

	//Select * from table Where name Like "%ram%"
	List<Employee> findByNameContaining(String keyword ,Sort sort);

	@Query("FROM Employee WHERE name = :name OR location = :location")
	List<Employee> getEmployeeByNameAndLocation(String name,String location);

	boolean existsByEmail(String email);


	List<Employee> findAll();
}
