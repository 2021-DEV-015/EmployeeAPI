package com.bnppf.employee.api.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bnppf.employee.api.BaseTest;
import com.bnppf.employee.api.domain.Employee;

@DataJpaTest
public class EmployeeRepositoryTest extends BaseTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	public void shouldSaveEmployeeWhenPassingEmployeeData() throws Exception {
		Employee employee = getEmployee();

		Employee savedEmployee = repository.save(employee);

		Assertions.assertEquals(ID, savedEmployee.getId());
		Assertions.assertEquals(EMPLOYEE_NAME, savedEmployee.getName());
		Assertions.assertEquals(ADDRESS, savedEmployee.getAddress());
		Assertions.assertNotNull(savedEmployee.getDateOfBirth());
		Assertions.assertEquals(employee.getDepartments().size(), savedEmployee
				.getDepartments().size());
		Assertions.assertEquals(ID, savedEmployee.getDepartments().stream()
				.findFirst().get().getId());
		Assertions.assertEquals(DEPARTMENT_NAME, savedEmployee.getDepartments()
				.stream().findFirst().get().getName());
	}

}
