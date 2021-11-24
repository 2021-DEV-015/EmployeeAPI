package com.bnppf.employee.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> fieldValidation(
			MethodArgumentNotValidException exception) {
		StringBuilder errorMessage = new StringBuilder();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			errorMessage.append(error.getDefaultMessage()).append(".");
		});

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(errorMessage.toString());
		return new ResponseEntity<ErrorResponse>(errorResponse,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<ErrorResponse> handleInvalidDate(
			InvalidDateFormatException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,
				HttpStatus.BAD_REQUEST);
	}
}
