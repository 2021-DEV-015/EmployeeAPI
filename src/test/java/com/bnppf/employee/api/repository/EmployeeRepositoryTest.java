package com.bnppf.employee.api.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bnppf.employee.api.domain.Department;
import com.bnppf.employee.api.domain.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {

	private static final String DEPARTMENT_NAME = "department1";
	private static final String ADDRESS = "22 Fairylane Circle, Dearborn, Michigan";
	private static final String EMPLOYEE_NAME = "Employee1";
	private static final int ID = 1;

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

	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setId(ID);
		employee.setName(EMPLOYEE_NAME);
		employee.setDateOfBirth(new Date(System.currentTimeMillis()));
		employee.setAddress(ADDRESS);

		List<Department> departments = new ArrayList<Department>();
		Department department = new Department();
		department.setId(ID);
		department.setName(DEPARTMENT_NAME);
		departments.add(department);
		employee.setDepartments(departments);
		return employee;
	}
}
