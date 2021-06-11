package com.aduro.employee.entity;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
// TODO: Auto-generated Javadoc

/**
 * The Interface MandatoryFieldValidator.
 *
 * @author kevin
 */
@Documented
@Constraint(validatedBy = MandatoryFieldValidatorCheck.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface MandatoryFieldValidator {
    
    /**
     * Message.
     *
     * @return the string
     */
    String message() default "{This is a required Field}";
    
    /**
     * Groups.
     *
     * @return the class[]
     */
    Class<?>[] groups() default {};
    
    /**
     * Payload.
     *
     * @return the class<? extends payload>[]
     */
    Class<? extends Payload>[] payload() default {};
    
    /**
     * Type.
     *
     * @return the string
     */
    String type() default "mandatory";
    
    /**
     * Size.
     *
     * @return the int
     */
    int size() default 0;
}
