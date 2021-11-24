package com.bnppf.employee.api.domain;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EmployeeDTO {

	@NotNull(message = "employee id is mandatory")
	private Integer id;

	@NotEmpty(message = "employee name is mandatory")
	private String name;
	private String dateOfBirth;
	private String address;
	private List<DepartmentDTO> departments;

}
