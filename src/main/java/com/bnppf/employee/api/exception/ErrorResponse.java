package com.bnppf.employee.api.exception;

import lombok.Data;

@Data
public class ErrorResponse {
	private int code;
	private String message;

}
