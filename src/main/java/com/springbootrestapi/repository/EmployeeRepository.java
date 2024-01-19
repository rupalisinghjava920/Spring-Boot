package com.springbootrestapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springbootrestapi.model.Employee;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{

	
	Employee save(Employee employee);
	
	Optional<Employee> findById(Long id);

	void deleteById(Long id);

	List<Employee> findByName(String name); 
	
	//Select * from table where name="Seete" And location="India"
	List<Employee> findByNameAndLocation(String name,String location);

	//Select * from table Where name Like "%ram%"
	List<Employee> findByNameContaining(String keyword ,Sort sort);

	@Query("FROM Employee WHERE name = :name OR location = :location")
	List<Employee> getEmployeeByNameAndLocation(String name,String location);

	boolean existsByEmail(String email);

	
}
