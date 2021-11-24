package com.bnppf.employee.api.exception;

public class InvalidDateFormatException extends Exception {

	private static final long serialVersionUID = -3248951956988731715L;
	
	public InvalidDateFormatException(String errorMessage) {
		super(errorMessage);
	}

}
