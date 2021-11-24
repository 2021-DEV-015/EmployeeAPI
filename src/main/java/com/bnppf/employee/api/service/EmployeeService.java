package com.bnppf.employee.api.service;

import java.util.Date;

import com.bnppf.employee.api.domain.EmployeeDTO;

public class EmployeeService {

	public EmployeeDTO create() {
		EmployeeDTO employee = new EmployeeDTO();
		employee.setId(1);
		employee.setName("Employee1");
		employee.setDateOfBirth(new Date());
		employee.setAddress("22 Fairylane Circle, Dearborn, Michigan");
		return employee;
	}

}