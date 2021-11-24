package com.bnppf.employee.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnppf.employee.api.domain.Department;
import com.bnppf.employee.api.domain.DepartmentDTO;
import com.bnppf.employee.api.domain.Employee;
import com.bnppf.employee.api.domain.EmployeeDTO;
import com.bnppf.employee.api.exception.InvalidDateFormatException;
import com.bnppf.employee.api.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public EmployeeDTO create(EmployeeDTO employeeDTO) throws InvalidDateFormatException {
		Employee employee = transformToEmployee(employeeDTO);
		Employee createdEmployee = repository.save(employee);
		return transformToDTO(createdEmployee);
	}

	private EmployeeDTO transformToDTO(Employee createdEmployee) {
		EmployeeDTO transformedEmployee = new EmployeeDTO();
		transformedEmployee.setId(createdEmployee.getId());
		transformedEmployee.setName(createdEmployee.getName());
		transformedEmployee.setAddress(createdEmployee.getAddress());
		transformedEmployee
				.setDateOfBirth(createdEmployee.getDateOfBirth() != null ? createdEmployee
						.getDateOfBirth().toString() : null);

		List<DepartmentDTO> departmentDTO = createdEmployee.getDepartments()
				.stream().map(department -> new DepartmentDTO(department.getId(), department.getName()))
				.collect(Collectors.toList());
		transformedEmployee.setDepartments(departmentDTO);
		return transformedEmployee;
	}

	private Employee transformToEmployee(EmployeeDTO employeeDTO) throws InvalidDateFormatException {
		Employee employee = new Employee();
		employee.setId(employeeDTO.getId());
		employee.setName(employeeDTO.getName());
		if (null != employeeDTO.getDateOfBirth()) {
			populateDateOfBirth(employeeDTO, employee);
		}
		employee.setAddress(employeeDTO.getAddress());

		List<Department> departments = employeeDTO.getDepartments().stream()
				.map(department -> new Department(department.getId(), department.getName(), employee))
				.collect(Collectors.toList());
		employee.setDepartments(departments);
		return employee;
	}

	private void populateDateOfBirth(EmployeeDTO employeeDTO, Employee employee)
			throws InvalidDateFormatException {
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			dateFormatter.parse(employeeDTO.getDateOfBirth());
			employee.setDateOfBirth(java.sql.Date.valueOf(employeeDTO
					.getDateOfBirth()));
		} catch (ParseException exception) {
			throw new InvalidDateFormatException("dateOfBirth: date format is invalid. It must be yyyy-MM-dd");
		}
	}
	
	public EmployeeDTO fetchByEmployeeId(Integer employeeId) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		Optional<Employee> optional = repository.findById(employeeId);
		if (optional.isPresent()) {
			employeeDTO = transformToDTO(optional.get());
		}
		return employeeDTO;
	}

}
