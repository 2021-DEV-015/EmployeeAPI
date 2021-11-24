package com.bnppf.employee.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnppf.employee.api.domain.EmployeeDTO;

@SpringBootTest
public class EmployeeServiceTest {

	@Test
	public void shouldReturnCreatedEmployeeWhenCreateServiceIsCalled()
			throws Exception {
		EmployeeService service = new EmployeeService();
		EmployeeDTO employee = service.create();

		Assertions.assertEquals(1, employee.getId());
		Assertions.assertEquals("Employee1", employee.getName());
		Assertions.assertEquals("22 Fairylane Circle, Dearborn, Michigan",
				employee.getAddress());
		Assertions.assertNotNull(employee.getDateOfBirth());
	}
}
