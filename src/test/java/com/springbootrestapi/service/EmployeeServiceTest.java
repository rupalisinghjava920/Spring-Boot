package com.springbootrestapi.service;

import com.springbootrestapi.model.Employee;
import com.springbootrestapi.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void EmployeeService_CreatedEmployee_ReturnEmployee(){
        Employee employee=new Employee();
        employee.setName("Radha");
        employee.setAge(67);
        employee.setLocation("Lucknow");
        employee.setDepartment("Java");

        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
        Employee saveEmployee=employeeService.saveEmployee(employee);
        Assertions.assertThat(saveEmployee).isNotNull();
    }

    @Test
    public void EmployeeService_GetAllEmployee_ReturnEmployee(){
        Page<Employee> employees=Mockito.mock(Page.class);
        when(employeeRepository.findAll(Mockito.any(Pageable.class))).thenReturn(employees);
        List<Employee> employee=  employeeService.getEmployees(1,5);
        Assertions.assertThat(employee).isNotNull();
    }


    @Test
    public void EmployeeService_GetEmployeeById_ReturnEmployee(){
        Employee employee=new Employee();
        employee.setName("Radha");
        employee.setAge(67);
        employee.setLocation("Lucknow");
        employee.setDepartment("Java");

        when(employeeRepository.findById(52)).thenReturn(Optional.ofNullable(employee));
        Employee saveEmployee=employeeService.getSingleEmployee((52));
        Assertions.assertThat(saveEmployee).isNotNull();
    }

    @Test
    public void EmployeeService_UpdateEmployee_ReturnEmployee() {
        int employeeId=1;

        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setName("Radha");
        employee.setDepartment("java");
        employee.setLocation("noida");
        employee.setAge(34);

        lenient().when(employeeRepository.findById(employeeId)).thenReturn(Optional.ofNullable(employee));
        when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);
        Employee updatedEmployee = employeeService.updateEmployee(employee,employeeId);

        Assertions.assertThat(updatedEmployee).isNotNull();
    }

}
