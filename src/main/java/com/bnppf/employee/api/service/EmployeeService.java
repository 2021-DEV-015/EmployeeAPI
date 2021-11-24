package com.bnppf.employee.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnppf.employee.api.domain.Department;
import com.bnppf.employee.api.domain.DepartmentDTO;
import com.bnppf.employee.api.domain.Employee;
import com.bnppf.employee.api.domain.EmployeeDTO;
import com.bnppf.employee.api.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public EmployeeDTO create(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setId(employeeDTO.getId());
		employee.setName(employeeDTO.getName());
		employee.setDateOfBirth(new java.sql.Date(employeeDTO.getDateOfBirth()
				.getTime()));
		employee.setAddress(employeeDTO.getAddress());

		List<Department> departments = employeeDTO.getDepartments().stream()
				.map(p -> new Department(p.getId(), p.getName()))
				.collect(Collectors.toList());
		employee.setDepartments(departments);

		Employee createdEmployee = repository.save(employee);

		EmployeeDTO transformedEmployee = new EmployeeDTO();
		transformedEmployee.setId(createdEmployee.getId());
		transformedEmployee.setName(createdEmployee.getName());
		transformedEmployee.setAddress(createdEmployee.getAddress());
		transformedEmployee.setDateOfBirth(createdEmployee.getDateOfBirth());

		List<DepartmentDTO> departmentDTO = createdEmployee.getDepartments()
				.stream().map(p -> new DepartmentDTO(p.getId(), p.getName()))
				.collect(Collectors.toList());
		transformedEmployee.setDepartments(departmentDTO);

		return transformedEmployee;
	}

}
