package com.springbootrestapi.repository;

import com.springbootrestapi.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void EmployeeRepository_Save_ReturnSavedEmployee(){
        Employee employee=new Employee();

        employee.setName("Rupali");
        employee.setAge(43);
        employee.setLocation("Nodia");
        //employee.setEmail("riet@gmail.com");
        employee.setDepartment("java");

        Employee savedEmployee=employeeRepository.save(employee);
        System.out.println("Saved Employee: " + savedEmployee);

        //assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void EmployeeRepository_GetAll_ReturnMoreThanOneEmployee() {

        Employee employee = new Employee();
        employee.setName("Rupali");
        employee.setAge(43);
        employee.setLocation("Noida");
       // employee.setEmail("riet@gmail.com");
        employee.setDepartment("Java");

        employeeRepository.save(employee);

        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println("Get All Employees: " + employeeList);

        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(12);
    }

    @Test
    public void EmployeeRepository_FindById_ReturnEmployee() {

        Employee employee = new Employee();
        employee.setName("Rupali");
        employee.setAge(43);
        employee.setLocation("Noida");
        // employee.setEmail("riet@gmail.com");
        employee.setDepartment("Java");

        employeeRepository.save(employee);

        Employee employeeList=employeeRepository.findById(employee.getId()).get();
        System.out.println("Get By Id Employees: " + employeeList);

        Assertions.assertThat(employeeList).isNotNull();

    }

    @Test
    public void EmployeeRepository_FindByName_ReturnEmployee() {

        Employee employee = new Employee();
        employee.setName("Rupali");
        employee.setAge(43);
        employee.setLocation("Noida");
        // employee.setEmail("riet@gmail.com");
        employee.setDepartment("Java");

        employeeRepository.save(employee);

        List<Employee> employeeList=  employeeRepository.findByName(employee.getName());
        System.out.println("List Name Employees: " + employeeList);

        Assertions.assertThat(employeeList).isNotNull();
    }

    @Test
    public void EmployeeRepository_UpdateEmployee_ReturnEmployeeNotNull() {

        Employee employee = new Employee();
        employee.setName("Shyam");
        employee.setAge(56);
        employee.setLocation("Kanpur");
        employee.setDepartment("Java");

        employeeRepository.save(employee);

        Employee employeeList=  employeeRepository.findById(employee.getId()).get();
        System.out.println("Save Employees: " + employeeList);

        employeeList.setName("SOhn");
        employeeList.setDepartment("Python");

        Employee updateEmployee=employeeRepository.save(employeeList);
        System.out.println("Update Employees: " + employeeList);

        Assertions.assertThat(updateEmployee.getName()).isNotNull();
        Assertions.assertThat(updateEmployee.getDepartment()).isNotNull();
    }

    @Test
    public void EmployeeRepository_DeleteEmployee_ReturnEmployeeIsEmpty() {

        Employee employee = new Employee();
        employee.setName("Shyam");
        employee.setAge(56);
        employee.setLocation("Kanpur");
        employee.setDepartment("Java");

        employeeRepository.save(employee);

        employeeRepository.deleteById(employee.getId());
        Optional<Employee> returnEmployee= employeeRepository.findById(employee.getId());

        Assertions.assertThat(returnEmployee).isEmpty();

    }



}
