package jp.co.axa.apidemo.controllers;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.EmployeeDetailsDTO;
import jp.co.axa.apidemo.services.EmployeeService;

@SpringBootTest
public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private ModelMapper modelMapper;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getEmployeesTest() throws Exception {
		List<Employee> employees = new ArrayList<>();
		Employee empOne = new Employee(1L, "Alex", 1000000, "IT");
		Employee empTwo = new Employee(2L, "Adam", 2000000, "HR");
		employees.add(empOne);
		employees.add(empTwo);

		when(employeeService.retrieveEmployees()).thenReturn(employees);
		ResponseEntity<List<EmployeeDetailsDTO>> response =  employeeController.getEmployees();
		assertNotNull(response);
		verify(employeeService, times(1)).retrieveEmployees();
	}
	
	@Test
	public void getEmployeeTest() {
		Employee employee = new Employee(1L, "Alex", 1000000, "IT");
		
		when(employeeService.getEmployee(1L)).thenReturn(employee);
		ResponseEntity<EmployeeDetailsDTO> response = employeeController.getEmployee(1L);
		assertNotNull(response);
		verify(employeeService, times(1)).getEmployee(1L);
	}
	
	@Test
	public void saveEmployeeTest() throws ParseException {
		EmployeeDetailsDTO employeeDTO = new EmployeeDetailsDTO();
		employeeDTO.setName("Alex");
		employeeDTO.setSalary(1000000);
		employeeDTO.setDepartment("IT");
		Employee employee = new Employee(1L, "Alex", 1000000, "IT");
		when(modelMapper.map(employeeDTO, Employee.class)).thenReturn(employee);
		ResponseEntity<String> response = employeeController.saveEmployee(employeeDTO);
		assertEquals(response.getBody(), "Employee Saved Successfully");
		verify(employeeService, times(1)).saveEmployee(employee);
	}
	
	@Test
	public void deleteEmployeeTest() {
		ResponseEntity<String> response = employeeController.deleteEmployee(1L);
		assertEquals(response.getBody(), "Employee Deleted Successfully");
		verify(employeeService, times(1)).deleteEmployee(1L);
	}
	
	@Test
	public void updateEmployeeTest() throws ParseException {
		
		EmployeeDetailsDTO employeeDTO = new EmployeeDetailsDTO();
		employeeDTO.setName("Alex");
		employeeDTO.setSalary(1000000);
		employeeDTO.setDepartment("IT");
		
		Employee employee = new Employee(1L, "Alex", 1000000, "IT");
		
		when(modelMapper.map(employeeDTO, Employee.class)).thenReturn(employee);
		when(employeeService.getEmployee(1L)).thenReturn(employee);
		
		ResponseEntity<String> response = employeeController.updateEmployee(employeeDTO, 1L);
		
		assertEquals(response.getBody(), "Employee Updated Successfully");
		verify(employeeService, times(1)).updateEmployee(employee);
		
	}
}
