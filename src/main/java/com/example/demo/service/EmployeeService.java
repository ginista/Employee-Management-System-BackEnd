package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.EmployeesRepository;

import jakarta.persistence.EntityNotFoundException;

import com.example.demo.domain.Employees;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeesRepository employeeRepository;
	
	//Create
	public Employees saveEmployee(Employees std) {
		return employeeRepository.save(std);
	}
	
	//Read
	
	/*public Employees findByEmployees(Integer id) {
		Optional<Employees> employee = Optional.ofNullable(employeeRepository.getById(id));
		if(!employee.isPresent())
			throw new ResourceNotFoundException("Employee not found with "+id);
		return employee.get();
		
	}*/
	public Employees findByEmployees(Integer id) {
	    try {
	        return employeeRepository.getReferenceById(id);
	    } catch (EntityNotFoundException ex) {
	        throw new ResourceNotFoundException("Employee not found with ID: " + id);
	    }
	}
	//findall
	 public List<Employees> getAllEmployees() {
	        return employeeRepository.findAll();
	    }
	
	//update
	public Employees updateEmployee(Employees std) {
		Employees s = employeeRepository.getReferenceById(std.getEmpId());
		if(s!=null) {
			s.setFirstName(std.getFirstName());
			s.setLastName(std.getLastName());
			s.setEmail(std.getEmail());
		}
		return employeeRepository.save(s);
	}
	
	//delete
	public Employees deleteEmployee(Employees emp) {
		int id = emp.getEmpId();
		employeeRepository.deleteById(id);
		return emp;
		
	}
	
	public Employees deleteEmployeeById(Integer id) {
		employeeRepository.deleteById(id);
		return employeeRepository.getReferenceById(id);
	}
	
	
}
