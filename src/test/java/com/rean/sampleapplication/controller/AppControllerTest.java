package com.junaid18183.sampleapplication.controller;

import static org.mockito.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.junaid18183.sampleapplication.config.TestUtils;
import com.junaid18183.sampleapplication.model.User;
import com.junaid18183.sampleapplication.service.UserService;

@WebMvcTest(value = RestApiController.class, secure = false)
public class AppControllerTest {

	@ClassRule
	public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	public AppControllerTest() {

	}
	
	@Test
	public void getHomePage() throws Exception {

		List<User> userList = new ArrayList<>();
		User mockedUser = new User();
		mockedUser.setName("Lily");
		mockedUser.setAge(26);
		mockedUser.setSalary(50000);
		userList.add(mockedUser);

		Mockito.when(userService.findAllUsers()).thenReturn(userList);

		RequestBuilder requestBuilder = TestUtils.getGetRequestBuilder("/");
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			//.andExpect(MockMvcResultMatchers.content().json("[{\"name\":\"Lily\",\"age\":26,\"salary\":50000}]"))
			.andReturn();

	}
	
	@Test
	public void getPartialPage() throws Exception {

		List<User> userList = new ArrayList<>();
		User mockedUser = new User();
		mockedUser.setName("Lily");
		mockedUser.setAge(26);
		mockedUser.setSalary(50000);
		userList.add(mockedUser);

		Mockito.when(userService.findAllUsers()).thenReturn(userList);

		RequestBuilder requestBuilder = TestUtils.getGetRequestBuilder("/partials/list");
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			//.andExpect(MockMvcResultMatchers.content().json("[{\"name\":\"Lily\",\"age\":26,\"salary\":50000}]"))
			.andReturn();

	}

}