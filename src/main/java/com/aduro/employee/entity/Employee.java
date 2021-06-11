package com.aduro.employee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class Employee.
 */
@Entity

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new employee.
 *
 * @param id the id
 * @param name the name
 * @param office the office
 * @param email the email
 * @param phone the phone
 * @param role the role
 */
@AllArgsConstructor

/**
 * Instantiates a new employee.
 */
@NoArgsConstructor
public class Employee {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	/** The name. */
	@MandatoryFieldValidator(size = 100)
    private String name;
	
	/** The office. */
	@MandatoryFieldValidator(type = "range", size = 4)
    private String office;
	
	/** The email. */
	@MandatoryFieldValidator(type = "email", size = 150)
    private String email;
	
	/** The phone. */
	@MandatoryFieldValidator(type = "phone", size = 12)
    private String phone;
	
	/** The role. */
	@MandatoryFieldValidator(size = 150)
    private String role;
}
