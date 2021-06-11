package com.aduro.employee.errorhandling;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// TODO: Auto-generated Javadoc

/**
 * Instantiates a new api error.
 */
@NoArgsConstructor

/**
 * Instantiates a new api error.
 *
 * @param status the status
 * @param message the message
 * @param errors the errors
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
public class ApiError {
    
    /** The status. */
    private HttpStatus status;
    
    /** The message. */
    private String message;
    
    /** The errors. */
    private List<String> errors;
}

