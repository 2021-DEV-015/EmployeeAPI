package com.bnppf.employee.api.domain;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDTO {

	private Integer id;
	private String name;
	private Date dateOfBirth;
	private String address;

}
