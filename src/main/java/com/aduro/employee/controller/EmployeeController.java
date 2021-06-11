/*
 * 
 */
package com.aduro.employee.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aduro.employee.entity.Employee;
import com.aduro.employee.model.Employees;
import com.aduro.employee.service.EmployeeService;

/**
 * The Class EmployeeController.
 * @author kevin
 */
@RestController
@Validated
@RequestMapping("/employees")
public class EmployeeController {
	
	/** The employee service. */
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * Fetch employees.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<Employees> fetchEmployees() {
		Employees employees = new Employees();
		employees.setEmployees(employeeService.findAll());
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	/**
	 * Fetch employee.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> fetchEmployee(@PathVariable("id") int id) {
		Optional<Employee> empData = employeeService.findById(id);
		if (empData.isPresent()) {
			return new ResponseEntity<>(empData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Employee found with id : " + id, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Delete employee.
	 *
	 * @param empId the emp id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") int empId) {
		String deleteMsg = "No employee found to delete";
		Optional<Employee> empData = employeeService.findById(empId);
		if (empData.isPresent()) {
			String employee = empData.get().getName();
			employeeService.deleteById(empId);
			deleteMsg = "Employee " + employee + " deleted";
			return new ResponseEntity<>(deleteMsg, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(deleteMsg, HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.save(employee);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
	/**
	 * Update.
	 *
	 * @param employee the employee
	 * @return the response entity
	 */
	@PutMapping
	public ResponseEntity<Object> update(@RequestBody Employee employee) {
		Optional<Employee> empData = employeeService.findById(employee.getId());
		if (empData.isPresent()) {
			Employee emp = employeeService.save(employee);
			return new ResponseEntity<>(emp, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("No Employee found with id : " + employee.getId(), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Delete all employees.
	 *
	 * @return the response entity
	 */
	@DeleteMapping
	public ResponseEntity<String> deleteAllEmployees() {
		boolean deletePerformed = employeeService.deleteAll();
		if(deletePerformed)
			return new ResponseEntity<>("Delete Successfull", HttpStatus.OK);
		else
			return new ResponseEntity<>("No Employees found to delete", HttpStatus.NOT_FOUND);
	}

}
