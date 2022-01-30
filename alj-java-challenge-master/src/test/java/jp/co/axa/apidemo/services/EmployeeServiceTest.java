package jp.co.axa.apidemo.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Mock
	private EmployeeRepository employeeRepository;	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void retrieveEmployeesTest() {
		
		List<Employee> employees = new ArrayList<>();
		
		Employee empOne = new Employee(1L, "Alex", 1000000, "IT");
		Employee empTwo = new Employee(2L, "Adam", 2000000, "HR");

		employees.add(empOne);
		employees.add(empTwo);

		when(employeeRepository.findAll()).thenReturn(employees);

		//test
		List<Employee> empList = employeeServiceImpl.retrieveEmployees();

		assertEquals(2, empList.size());
		verify(employeeRepository, times(1)).findAll();	
	}
	
	@Test
	public void getEmployeeTest() {
		
		Optional<Employee> employee = Optional.of(new Employee(1L, "Alex", 1000000, "IT"));
		
		when(employeeRepository.findById(1L)).thenReturn(employee);
		
		Employee response = employeeServiceImpl.getEmployee(1L);
		
		assertNotNull(response);
		verify(employeeRepository, times(1)).findById(1L);	
	}
	
	@Test
	public void saveEmployeeTest() {
		
		Employee employee = new Employee(1L, "Alex", 1000000, "IT");
		
		employeeServiceImpl.saveEmployee(employee);
		
		verify(employeeRepository, times(1)).save(employee);	
	}
	
	@Test
	public void updateEmployeeTest() {
		
		Employee employee = new Employee(1L, "Alex", 1000000, "IT");
		
		employeeServiceImpl.updateEmployee(employee);
		
		verify(employeeRepository, times(1)).save(employee);	
	}
	
	@Test
	public void deleteEmployeeTest() {
		
		employeeServiceImpl.deleteEmployee(1L);
		
		verify(employeeRepository, times(1)).deleteById(1L);	
	}
	
}
