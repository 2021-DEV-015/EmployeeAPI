package com.bnppf.employee.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bnppf.employee.api.domain.DepartmentDTO;
import com.bnppf.employee.api.domain.EmployeeDTO;

@Service
public class EmployeeService {

	public EmployeeDTO create() {
		EmployeeDTO employee = new EmployeeDTO();
		employee.setId(1);
		employee.setName("Employee1");
		employee.setDateOfBirth(new Date());
		employee.setAddress("22 Fairylane Circle, Dearborn, Michigan");
		List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();
		DepartmentDTO department = new DepartmentDTO();
		department.setId(1);
		department.setName("department1");
		departments.add(department);
		employee.setDepartments(departments);
		return employee;
	}

}
