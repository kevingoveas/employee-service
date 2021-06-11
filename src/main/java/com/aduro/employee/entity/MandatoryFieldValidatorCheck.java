package com.aduro.employee.entity;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
// TODO: Auto-generated Javadoc

/**
 * The Class MandatoryFieldValidatorCheck.
 *
 * @author kevin
 * Provides Null/Empty/Size validations on the Fields.
 */
public class MandatoryFieldValidatorCheck implements ConstraintValidator<MandatoryFieldValidator, String> {

    /** The type. */
    private String type;
    
    /** The size. */
    private int size;

    /**
     * Initialize.
     *
     * @param constraint the constraint
     */
    @Override
    public void initialize(MandatoryFieldValidator constraint) {
        this.type = constraint.type();
        this.size = constraint.size();
    }

    /**
     * Checks if is valid.
     *
     * @param s the s
     * @param constraintValidatorContext the constraint validator context
     * @return true, if is valid
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    	boolean isValid = true;
    	StringBuilder invalidMessage = new StringBuilder("Mandatory fields");
    	if(null == s) {
    		invalidMessage.append(" Value cannot be null");
    		isValid = false;
    	} else if(s.isBlank()) {
    		invalidMessage.append(" Value cannot be empty");
    		isValid = false;
    	} else if (s.length() > this.size) {
    		invalidMessage.append(" Exceeds char size limit of " + this.size);
    		isValid = false;
    	}
    	if(isValid && type.equals("range"))
			isValid = checkRange(s, invalidMessage);
    	if(isValid && type.equals("phone"))
			isValid = checkPhone(s, invalidMessage);
    	if(isValid && type.equals("email"))
			isValid = checkEmail(s, invalidMessage);
    	if(!isValid)
    		updateValidatorContext(constraintValidatorContext, invalidMessage.toString());
        return isValid;
    }

	/**
	 * Check phone.
	 *
	 * @param s the s
	 * @param invalidMessage the invalid message
	 * @return true, if successful
	 */
	private boolean checkPhone(String s, StringBuilder invalidMessage) {
		boolean isValid = Pattern.compile("^[1-9]\\d{2}\\.\\d{3}\\.\\d{4}").matcher(s).matches();
		if(!isValid)
			invalidMessage.append(" Invalid format. Expected format 123.234.6789");
		return isValid;
	}

	/**
	 * Check email.
	 *
	 * @param s the s
	 * @param invalidMessage the invalid message
	 * @return true, if successful
	 */
	private boolean checkEmail(String s, StringBuilder invalidMessage) {
		//Minimal check
		boolean isValid = s.contains("@");
		if(!isValid)
			invalidMessage.append(" Invalid format. Enter valid email");
		return isValid;
	}

	/**
	 * Update validator context.
	 *
	 * @param constraintValidatorContext the constraint validator context
	 * @param invalidMessage the invalid message
	 */
	private void updateValidatorContext(ConstraintValidatorContext constraintValidatorContext, String invalidMessage) {
		constraintValidatorContext.disableDefaultConstraintViolation();
		constraintValidatorContext.buildConstraintViolationWithTemplate(
		        "{com.aduro.state.message." + invalidMessage.toString() + "}").addConstraintViolation();
	}

	/**
	 * Check range.
	 *
	 * @param s the s
	 * @param msg the msg
	 * @return true, if successful
	 */
	private boolean checkRange(String s, StringBuilder msg) {
		boolean isValidRange = true;
		if (!withinNumberRange(s) || !withinCharRange(s)) {
			msg.append(" Invalid Range. Expected Range 100A-599F");
			isValidRange = false;
		} 
		return isValidRange;
	}
	
	/**
	 * Within number range.
	 *
	 * @param s the s
	 * @return true, if successful
	 */
	private boolean withinNumberRange(String s) {
		boolean validNumberRange = false;
		String str = s.substring(0, s.length() - 1);
		try {
			if (Integer.parseInt(str) < 600 && Integer.parseInt(str) > 99)
				validNumberRange = true;
		} catch(NumberFormatException e) {}
		return validNumberRange;
	}
	
	/**
	 * Within char range.
	 *
	 * @param s the s
	 * @return true, if successful
	 */
	private boolean withinCharRange(String s) {
		boolean validCharRange = false;
		char alphabet = s.charAt(s.length() - 1);
		validCharRange = Character.isAlphabetic(alphabet) && ((alphabet >= 'a' && alphabet <= 'f') || (alphabet >= 'A' && alphabet <= 'F')) ;
		return validCharRange;	
	}
}
