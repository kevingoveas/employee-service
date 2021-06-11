package com.aduro.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aduro.employee.entity.Employee;
import com.aduro.employee.repository.EmployeeRepository;
// TODO: Auto-generated Javadoc

/**
 * The Class EmployeeService.
 */
@Service
public class EmployeeService {
	
	/** The employee repository. */
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	/**
	 * Find by id.
	 *
	 * @param empId the emp id
	 * @return the optional
	 */
	public Optional<Employee> findById(int empId) {
		return employeeRepository.findById(empId);
	}

	/**
	 * Save.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	public Employee save(Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);
		return savedEmployee;
	}

	/**
	 * Delete by id.
	 *
	 * @param empId the emp id
	 */
	public void deleteById(int empId) {
		employeeRepository.deleteById(empId);
	}

	/**
	 * Delete all.
	 *
	 * @return true, if successful
	 */
	public boolean deleteAll() {
		boolean hasRecords = employeeRepository.count() > 0;
		if(hasRecords)
			employeeRepository.deleteAll();
		return hasRecords;
	}
	
}
