package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer>{

	
}
