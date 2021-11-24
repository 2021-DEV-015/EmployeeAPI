package com.bnppf.employee.api.repository;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bnppf.employee.api.domain.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	public void shouldSaveEmployeeWhenPassingEmployeeData() throws Exception {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Employee1");
		Calendar calendar = Calendar.getInstance();
		employee.setDateOfBirth(new Date(calendar.getTimeInMillis()));
		employee.setAddress("22 Fairylane Circle, Dearborn, Michigan");

		Employee savedEmployee = repository.save(employee);

		Assertions.assertEquals(1, savedEmployee.getId());
		Assertions.assertEquals("Employee1", savedEmployee.getName());
		Assertions.assertEquals("22 Fairylane Circle, Dearborn, Michigan",
				savedEmployee.getAddress());
		Assertions.assertNotNull(savedEmployee.getDateOfBirth());
	}
}
