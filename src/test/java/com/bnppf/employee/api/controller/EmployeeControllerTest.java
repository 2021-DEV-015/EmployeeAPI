package com.bnppf.employee.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnppf.employee.api.domain.EmployeeDTO;

@SpringBootTest
public class EmployeeControllerTest {

	@Test
	public void shouldReturnEmployeeWhenCreateEmployeeAPIServiceIsCalled()
			throws Exception {
		EmployeeController controller = new EmployeeController();
		EmployeeDTO employee = controller.createEmployee();

		Assertions.assertNotNull(employee);
	}
}
