package com.bnppf.employee.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bnppf.employee.api.domain.EmployeeAlreadyExistsException;

@ControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> fieldValidation(
			MethodArgumentNotValidException exception) {
		StringBuilder errorMessage = new StringBuilder();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			errorMessage.append(error.getDefaultMessage()).append(".");
		});

		return getErrorResponse(errorMessage.toString(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<ErrorResponse> handleInvalidDate(
			InvalidDateFormatException exception) {
		return getErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeAlreadyExists(
			EmployeeAlreadyExistsException exception) {
		return getErrorResponse(exception.getMessage(),
				HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRecordNotFound(
			RecordNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
	}

	private ResponseEntity<ErrorResponse> getErrorResponse(String errorMessage,
			HttpStatus status) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(status.value());
		errorResponse.setMessage(errorMessage);
		return new ResponseEntity<ErrorResponse>(errorResponse, status);
	}
}
