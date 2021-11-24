package com.bnppf.employee.api.controller;

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

import com.bnppf.employee.api.BaseTest;
import com.bnppf.employee.api.domain.EmployeeDTO;
import com.bnppf.employee.api.service.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest extends BaseTest {

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

	@Test
	public void shouldReturnBadRequestWhenPassingEmployeeIdAsNullWhileCreatingEmployeeData()
			throws Exception {
		String employeeRequestJson = "{\"name\":\"Employee1\",\"address\":\"22 Fairylane Circle, Dearborn, Michigan\",\"dateOfBirth\":\"07-07-1990\",\"departments\":[{\"id\":1,\"name\":\"department1\"}]}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/employee").accept(MediaType.APPLICATION_JSON)
				.content(employeeRequestJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();

		Assertions.assertEquals(400, result.getResponse().getStatus());
		JSONAssert.assertEquals(
				"{\"code\":400,\"message\":\"employee id is mandatory.\"}",
				result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void shouldReturnBadRequestWhenPassingEmployeeNameAsEmptyWhileCreatingEmployeeData()
			throws Exception {
		String employeeRequestJson = "{\"id\":1,\"name\":\"\",\"address\":\"22 Fairylane Circle, Dearborn, Michigan\",\"dateOfBirth\":\"07-07-1990\",\"departments\":[{\"id\":1,\"name\":\"department1\"}]}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/employee").accept(MediaType.APPLICATION_JSON)
				.content(employeeRequestJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();

		Assertions.assertEquals(400, result.getResponse().getStatus());
		JSONAssert.assertEquals(
				"{\"code\":400,\"message\":\"employee name is mandatory.\"}",
				result.getResponse().getContentAsString(), false);
	}

}
