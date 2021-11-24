package com.bnppf.employee.api.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnppf.employee.api.BaseTest;
import com.bnppf.employee.api.domain.Employee;
import com.bnppf.employee.api.domain.EmployeeDTO;
import com.bnppf.employee.api.exception.InvalidDateFormatException;
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

	@Test
	public void shouldThrowInvalidDateFormatExceptionWhenEmployeeDateOfBirthIsText()
			throws Exception {
		EmployeeDTO employeeToBeCreated = getEmployeeDTO();
		employeeToBeCreated.setDateOfBirth("text");

		InvalidDateFormatException exception = Assertions.assertThrows(
				InvalidDateFormatException.class, () -> {
					service.create(employeeToBeCreated);
				});

		Assertions.assertEquals(
				"dateOfBirth: date format is invalid. It must be yyyy-MM-dd",
				exception.getMessage());
	}
	
	@Test
	public void shouldReturnEmployeeWhenPassingEmployeeId()
			throws Exception {
		Employee mockEmployee = getEmployee();
		Mockito.when(repository.findById(1)).thenReturn(
				Optional.of(mockEmployee));
		
		EmployeeDTO employee = service.fetchByEmployeeId(1);
		
		Assertions.assertEquals(ID, employee.getId());
		Assertions.assertEquals(EMPLOYEE_NAME, employee.getName());
		Assertions.assertEquals(ADDRESS, employee.getAddress());
		Assertions.assertNotNull(employee.getDateOfBirth());
		Assertions.assertEquals(employee.getDepartments().size(), employee
				.getDepartments().size());
		Assertions.assertEquals(ID, employee.getDepartments().stream()
				.findFirst().get().getId());
		Assertions.assertEquals(DEPARTMENT_NAME, employee.getDepartments().stream()
				.findFirst().get().getName());
	}
	
	@Test
	public void shouldReturnEmptyEmployeeWhenEmployeeRecordNotFound()
			throws Exception {
		EmployeeDTO employee = service.fetchByEmployeeId(4);
		
		Assertions.assertNull(employee.getId());
	}

}
