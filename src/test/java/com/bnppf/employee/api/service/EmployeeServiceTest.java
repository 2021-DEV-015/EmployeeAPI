package com.bnppf.employee.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnppf.employee.api.BaseTest;
import com.bnppf.employee.api.domain.DepartmentDTO;
import com.bnppf.employee.api.domain.Employee;
import com.bnppf.employee.api.domain.EmployeeDTO;
import com.bnppf.employee.api.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTest extends BaseTest {

	@Autowired
	private EmployeeService service;

	@Mock
	private EmployeeRepository repository;

	@Test
	public void shouldReturnCreatedEmployeeWhenCreateServiceIsCalled()
			throws Exception {
		EmployeeDTO employeeToBeCreated = getEmployeeDTO();

		Employee mockEmployee = getEmployee();
		Mockito.when(repository.save(Mockito.any(Employee.class))).thenReturn(
				mockEmployee);

		EmployeeDTO employee = service.create(employeeToBeCreated);

		Assertions.assertEquals(ID, employee.getId());
		Assertions.assertEquals(EMPLOYEE_NAME, employee.getName());
		Assertions.assertEquals(ADDRESS, employee.getAddress());
		Assertions.assertNotNull(employee.getDateOfBirth());
		Assertions.assertEquals(employee.getDepartments().size(), employee
				.getDepartments().size());
		Assertions.assertEquals(ID, employee.getDepartments().stream()
				.findFirst().get().getId());
		Assertions.assertEquals(DEPARTMENT_NAME, employee.getDepartments()
				.stream().findFirst().get().getName());
	}

	@Test
	public void shouldReturnCreatedEmployeeWhenEmployeeDateOfBirthIsNull()
			throws Exception {
		EmployeeDTO employeeToBeCreated = getEmployeeDTO();
		employeeToBeCreated.setDateOfBirth(null);

		Employee mockEmployee = getEmployee();
		mockEmployee.setDateOfBirth(null);
		Mockito.when(repository.save(Mockito.any(Employee.class))).thenReturn(
				mockEmployee);

		EmployeeDTO employee = service.create(employeeToBeCreated);

		Assertions.assertNull(employee.getDateOfBirth());
	}

	private EmployeeDTO getEmployeeDTO() {
		EmployeeDTO employeeToBeCreated = new EmployeeDTO();
		employeeToBeCreated.setId(ID);
		employeeToBeCreated.setName(EMPLOYEE_NAME);
		employeeToBeCreated.setDateOfBirth(new Date());
		employeeToBeCreated.setAddress(ADDRESS);
		List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();
		DepartmentDTO department = new DepartmentDTO(ID, DEPARTMENT_NAME);
		departments.add(department);
		employeeToBeCreated.setDepartments(departments);
		return employeeToBeCreated;
	}

}
