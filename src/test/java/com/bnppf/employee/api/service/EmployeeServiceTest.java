package com.bnppf.employee.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnppf.employee.api.domain.EmployeeDTO;

@SpringBootTest
public class EmployeeServiceTest {

	private static final String DEPARTMENT_NAME = "department1";
	private static final String ADDRESS = "22 Fairylane Circle, Dearborn, Michigan";
	private static final String EMPLOYEE_NAME = "Employee1";
	private static final int ID = 1;

	@Autowired
	private EmployeeService service;

	@Test
	public void shouldReturnCreatedEmployeeWhenCreateServiceIsCalled()
			throws Exception {
		EmployeeDTO employee = service.create();

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
}
