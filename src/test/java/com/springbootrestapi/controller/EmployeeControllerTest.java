package com.springbootrestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootrestapi.model.Employee;
import com.springbootrestapi.repository.EmployeeRepository;
import com.springbootrestapi.service.EmployeeServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;
    @MockBean
    private EmployeeServiceImpl employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    public void init(){
        employee=new Employee();
        employee.setId(1);
        employee.setName("Rupali");
        employee.setLocation("Noida");
        employee.setDepartment("Java");
        employee.setAge(45);

    }
    @Test
    public void EmployeeController_SaveEmployee_ReturnCreated() throws Exception{

        given(employeeService.saveEmployee(any(Employee.class)))
                .willAnswer((invocation -> invocation.getArguments()));
        ResultActions resultActions=mockMvc.perform(post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(employee.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(employee.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", CoreMatchers.is(employee.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department", CoreMatchers.is(employee.getDepartment())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", CoreMatchers.is(employee.getAge())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void EmployeeController_GetAllEmployee_ReturnEmployee() throws Exception {

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Rupali");
        employee.setLocation("Noida");
        employee.setDepartment("java");
        employee.setAge(23);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeService.getEmployees(1, 10)).thenReturn(employees);

        // Perform the GET request
        ResultActions resultActions = mockMvc.perform(get("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNumber", "1")
                .param("pageSize", "10"))
                 .andDo(MockMvcResultHandlers.print());
        // Assert the response
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].name", CoreMatchers.is(employee.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].age", CoreMatchers.is(employee.getAge())))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].location", CoreMatchers.is(employee.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].department", CoreMatchers.is(employee.getDepartment())));

                MvcResult result = resultActions.andReturn();
                System.out.println(result.getResponse().getContentAsString());

    }


    @Test
    public void EmployeeController_EmployeeDetail_ReturnCreated() throws Exception{
        int employeeId=2;

        given(employeeService.getSingleEmployee(2)).willReturn(employee);
        ResultActions resultActions=mockMvc.perform(get("/employees/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(employee.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(employee.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", CoreMatchers.is(employee.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department", CoreMatchers.is(employee.getDepartment())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", CoreMatchers.is(employee.getAge())));

        MvcResult result = resultActions.andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void EmployeeController_UpdateEmployee_ReturnEmployee() throws Exception {
        int employeeId = 1;

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John Doe");
        employee.setLocation("New York");
        employee.setDepartment("Engineering");
        employee.setAge(30);

        given(employeeService.updateEmployee(any(Employee.class), eq(1))).willReturn(employee);
        ResultActions resultActions = mockMvc.perform(put("/employee/{id}/update", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // Assert the status and the JSON response
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id", CoreMatchers.is(employee.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("name", CoreMatchers.is(employee.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("location", CoreMatchers.is(employee.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("department", CoreMatchers.is(employee.getDepartment())))
                .andExpect(MockMvcResultMatchers.jsonPath("age", CoreMatchers.is(employee.getAge())));

        // Optional: Print the result to the console for debugging
        MvcResult result = resultActions.andReturn();
        System.out.println(result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());

    }

    
    @Test
    public void EmployeeController_DeleteEmployee_ReturnString() throws Exception {
        int employeeId = 1;

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John Doe");
        employee.setLocation("New York");
        employee.setDepartment("Engineering");
        employee.setAge(30);

        doNothing().when(employeeService).deleteEmployee(employeeId);

        ResultActions resultActions = mockMvc.perform(delete("/employee/{id}/delete", employeeId)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        // Optionally, verify that the delete method was called with the correct employeeId
        verify(employeeService, times(1)).deleteEmployee(employeeId);

        MvcResult result = resultActions.andReturn();
        System.out.println(result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());

    }

}
