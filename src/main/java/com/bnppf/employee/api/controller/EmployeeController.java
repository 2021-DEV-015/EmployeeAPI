package com.bnppf.employee.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnppf.employee.api.domain.EmployeeAlreadyExistsException;
import com.bnppf.employee.api.domain.EmployeeDTO;
import com.bnppf.employee.api.exception.InvalidDateFormatException;
import com.bnppf.employee.api.exception.RecordNotFoundException;
import com.bnppf.employee.api.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employee)
			throws InvalidDateFormatException, EmployeeAlreadyExistsException {
		return service.create(employee);
	}

	@GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDTO fetchEmployeeById(@PathVariable Integer id)
			throws RecordNotFoundException {
		return service.fetchByEmployeeId(id);
	}

}
