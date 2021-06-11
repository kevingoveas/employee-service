package com.aduro.employee.model;

import java.util.List;
import com.aduro.employee.entity.Employee;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new employees.
 */
@Data
//Acts as a wrapper for backward compatibility and easy to add more properties without breaking contract.
public class Employees {
	
	/** The employees. */
	private List<Employee> employees;
}
