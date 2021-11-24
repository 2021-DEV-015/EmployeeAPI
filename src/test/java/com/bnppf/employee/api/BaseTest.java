package com.bnppf.employee.api;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.bnppf.employee.api.domain.Department;
import com.bnppf.employee.api.domain.Employee;

public class BaseTest {

	public static final String DEPARTMENT_NAME = "department1";
	public static final String ADDRESS = "22 Fairylane Circle, Dearborn, Michigan";
	public static final String EMPLOYEE_NAME = "Employee1";
	public static final int ID = 1;

	public Employee getEmployee() {
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
