package com.bnppf.employee.api.domain;

public class EmployeeAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 690659703587136104L;
	
	public EmployeeAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}

}
