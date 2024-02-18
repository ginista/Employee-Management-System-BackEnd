package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Employees;
import com.example.demo.exception.ErrorObject;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.EmployeeService;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

  //  @PreAuthorize("hasRole('ROLE_group1')")
    @PostMapping("/save")
    public ResponseEntity<Employees> saveEmployee(@RequestBody Employees employee) {
        Employees savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

 /*   @PreAuthorize("hasRole('ROLE_group1')")
    @GetMapping("/get-employee/{empId}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Integer empId) throws ResourceNotFoundException  {
        Employees employee = employeeService.findByEmployees(empId);
        
        return ResponseEntity.ok().body(employee);
        
       
    }*/
    @PreAuthorize("hasRole('ROLE_group1')")
    @GetMapping("/get-employee/{empId}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Integer empId) {
        try {
            Employees employee = employeeService.findByEmployees(empId);
            return ResponseEntity.ok().body(employee);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ErrorObject(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis()));
        }
    }
    
    @PreAuthorize("hasRole('ROLE_group1')")
    @GetMapping("/employees")
    public ResponseEntity<List<Employees>> getAllEmployees() {
        List<Employees> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_group1')")
    @PutMapping("/update-employee")
    public ResponseEntity<Employees> updateEmployee(@RequestBody Employees emp) {
        Employees updatedEmployee = employeeService.updateEmployee(emp);
        return ResponseEntity.ok(updatedEmployee);
    }

   // @PreAuthorize("hasRole('ROLE_group1')")
    @DeleteMapping("/delete-employee")
    public ResponseEntity<Employees> deleteEmployee(@RequestBody Employees emp) {
        Employees deletedEmployee = employeeService.deleteEmployee(emp);
        return ResponseEntity.ok(deletedEmployee);
    }
    
    
    @PreAuthorize("hasRole('ROLE_group1')")
    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<Employees> deleteEmployeeById(@PathVariable Integer id) {
         employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_group1')")
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome Employee Management Application Yeahh!!");
    }
    
}
