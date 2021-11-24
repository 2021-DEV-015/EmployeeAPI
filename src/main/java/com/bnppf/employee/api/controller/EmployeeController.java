package com.bnppf.employee.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnppf.employee.api.domain.EmployeeDTO;
import com.bnppf.employee.api.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employee) {
		return service.create(employee);
	}

}
