package com.aduro.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aduro.employee.entity.Employee;

/**
 * The Interface EmployeeRepository.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
