package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.EmployeeDetailsDTO;
import jp.co.axa.apidemo.services.EmployeeService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "This method is used to get the list of employees")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDetailsDTO>> getEmployees() {
    	LOG.info("Inside EmployeeController - getEmployees");
        List<Employee> employees = employeeService.retrieveEmployees();        
        return ResponseEntity
        		.ok(employees.stream().map(this::convertToDto).collect(Collectors.toList()));
    }
    
    @ApiOperation(value = "This method is used to fetch the employee details for given Employee ID")
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDetailsDTO> getEmployee(@PathVariable(name="employeeId")Long employeeId) {
    	LOG.info("Inside EmployeeController - getEmployee -- employeeId {}", employeeId);
        return ResponseEntity.ok(this.convertToDto(employeeService.getEmployee(employeeId)));
    }

    @ApiOperation(value = "This method is used to save the details of employees")
    @PostMapping("/employees")
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDetailsDTO employeeDetailsDTO) throws ParseException{
    	LOG.info("Inside EmployeeController - saveEmployee");
    	if(employeeDetailsDTO.getId() == null) {
    		employeeService.saveEmployee(this.convertToEntity(employeeDetailsDTO));
    		return ResponseEntity.ok("Employee Saved Successfully");
    	}
    	return ResponseEntity.badRequest().body("Remove EmployeeId or pass it as null");
        
    }

    @ApiOperation(value = "This method is used to delete the employee record for given Employee ID")
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
    	LOG.info("Inside EmployeeController - deleteEmployee -- employeeId {}", employeeId);
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }

    @ApiOperation(value = "This method is used to update the details of employee")
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDetailsDTO employeeDetailsDTO,
                               @PathVariable(name="employeeId")Long employeeId) throws ParseException{
    	LOG.info("Inside EmployeeController - updateEmployee -- employeeId {}", employeeId);
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(this.convertToEntity(employeeDetailsDTO));
            return ResponseEntity.ok("Employee Updated Successfully");
        }
        return ResponseEntity.badRequest().body("Employee ID does not exist");
    } 
    
    /* Entity to DTO Conversion */
    private EmployeeDetailsDTO convertToDto(Employee employee) {
    	EmployeeDetailsDTO employeeDetailsDTO = modelMapper.map(employee, EmployeeDetailsDTO.class);
        return employeeDetailsDTO;
    }
    
    /* DTO to Entity conversion */
    private Employee convertToEntity(EmployeeDetailsDTO employeeDetailsDTO) throws ParseException {
    	Employee employee = modelMapper.map(employeeDetailsDTO, Employee.class);
        return employee;
    }
}
