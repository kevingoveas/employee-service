package com.aduro.employee.errorhandling;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomResponseEntityExceptionHandler.
 */
@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

  /**
   * Handle.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler
  public ResponseEntity<ApiError> handle(ConstraintViolationException ex) {
       final List<String> errors = new ArrayList<String>();
       for (final ConstraintViolation<?> violation : ex.getConstraintViolations())
           errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
       final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
       return new ResponseEntity<ApiError>(apiError, null, HttpStatus.BAD_REQUEST);
  }
}
