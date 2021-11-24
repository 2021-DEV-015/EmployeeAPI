package com.bnppf.employee.api.exception;

public class RecordNotFoundException extends Exception {

	private static final long serialVersionUID = 3260428169077209718L;

	public RecordNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
