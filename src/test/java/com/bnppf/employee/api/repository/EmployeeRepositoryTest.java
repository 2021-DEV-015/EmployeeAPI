package com.bnppf.employee.api.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bnppf.employee.api.domain.Department;
import com.bnppf.employee.api.domain.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {

	private static final String ADDRESS = "22 Fairylane Circle, Dearborn, Michigan";
	private static final String EMPLOYEE_NAME = "Employee1";
	private static final int EMPLOYEE_ID = 1;
	@Autowired
	private EmployeeRepository repository;

	@Test
	public void shouldSaveEmployeeWhenPassingEmployeeData() throws Exception {
		Employee employee = new Employee();
		employee.setId(EMPLOYEE_ID);
		employee.setName(EMPLOYEE_NAME);
		Calendar calendar = Calendar.getInstance();
		employee.setDateOfBirth(new Date(calendar.getTimeInMillis()));
		employee.setAddress(ADDRESS);

		List<Department> departments = new ArrayList<Department>();
		Department department = new Department();
		department.setId(EMPLOYEE_ID);
		department.setName("department1");
		departments.add(department);
		employee.setDepartments(departments);

		Employee savedEmployee = repository.save(employee);

		Assertions.assertEquals(1, savedEmployee.getId());
		Assertions.assertEquals(EMPLOYEE_NAME, savedEmployee.getName());
		Assertions.assertEquals(ADDRESS, savedEmployee.getAddress());
		Assertions.assertNotNull(savedEmployee.getDateOfBirth());
		Assertions.assertEquals(employee.getDepartments().size(), savedEmployee
				.getDepartments().size());
		Assertions.assertEquals(EMPLOYEE_ID, savedEmployee.getDepartments()
				.stream().findFirst().get().getId());
		Assertions.assertEquals("department1", savedEmployee.getDepartments()
				.stream().findFirst().get().getName());
	}
}
