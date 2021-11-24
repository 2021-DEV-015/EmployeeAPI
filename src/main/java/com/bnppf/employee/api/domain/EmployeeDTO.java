package com.bnppf.employee.api.domain;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeDTO {

	private Integer id;
	private String name;
	private String dateOfBirth;
	private String address;
	private List<DepartmentDTO> departments;

}
