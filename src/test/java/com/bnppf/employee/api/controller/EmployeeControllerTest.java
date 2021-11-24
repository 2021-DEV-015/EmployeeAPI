package com.bnppf.employee.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bnppf.employee.api.domain.DepartmentDTO;
import com.bnppf.employee.api.domain.EmployeeDTO;
import com.bnppf.employee.api.service.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private EmployeeService service;

	@Test
	public void shouldReturnEmployeeWhenCreateEmployeeAPIServiceIsCalled()
			throws Exception {
		EmployeeDTO employee = getEmployeeDTO();
		Mockito.when(service.create(Mockito.any(EmployeeDTO.class)))
				.thenReturn(employee);

		String employeeRequestJson = "{\"id\":1,\"name\":\"Employee1\",\"address\":\"22 Fairylane Circle, Dearborn, Michigan\",\"dateOfBirth\":\"1990-07-07\",\"departments\":[{\"id\":1,\"name\":\"department1\"}]}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/employee").accept(MediaType.APPLICATION_JSON)
				.content(employeeRequestJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		Assertions.assertEquals(200, result.getResponse().getStatus());
		JSONAssert.assertEquals(employeeRequestJson, result.getResponse()
				.getContentAsString(), false);
	}

	private EmployeeDTO getEmployeeDTO() {
		EmployeeDTO employee = new EmployeeDTO();
		employee.setId(1);
		employee.setName("Employee1");
		employee.setDateOfBirth("1990-07-07");
		employee.setAddress("22 Fairylane Circle, Dearborn, Michigan");
		List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();
		DepartmentDTO department = new DepartmentDTO(1, "department1");
		departments.add(department);
		employee.setDepartments(departments);
		return employee;
	}
}
