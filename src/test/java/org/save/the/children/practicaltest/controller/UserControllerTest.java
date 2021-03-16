package org.save.the.children.practicaltest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.save.the.children.practicaltest.api.response.UserResponse;
import org.save.the.children.practicaltest.constant.Constants;
import org.save.the.children.practicaltest.constant.ResponseCodes;
import org.save.the.children.practicaltest.constant.ResponseMessage;
import org.save.the.children.practicaltest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void registerUserTest() throws Exception {

		User user = new User();
		user.setFirstName("Anil");
		user.setLastName("Anjuna");
		user.setAddressLine1("No 07");
		user.setAddressLine2("Rothwell Street");
		user.setCity("Bolton");
		user.setPostCode("BL3 6HY");
		user.setCountry("United Kingdom");
		user.setTitle("Mr");
		user.setIdentityNumber("903153806V");

		String jsonRequest = objectMapper.writeValueAsString(user);

		MvcResult result = mockMvc.perform(post("/api/v1/user").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

		String resultContex = result.getResponse().getContentAsString();

		UserResponse response = objectMapper.readValue(resultContex, UserResponse.class);

		Assert.assertEquals(Constants.SUCCESS,response.getStatus());
		Assert.assertEquals(ResponseMessage.USER_REGISTER_SUCCESS,response.getMessage());
		Assert.assertEquals(ResponseCodes.USER_REGISTER_SUCCESS,response.getCode());
	}

	@Test
	public void getUserInfoTest() throws Exception {
		
		User user = new User();
		user.setId(1L);

		MvcResult result = mockMvc.perform(get("/api/v1/user/info").param("id", user.getId().toString()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String resultContex = result.getResponse().getContentAsString();

		UserResponse response = objectMapper.readValue(resultContex, UserResponse.class);

		Assert.assertEquals(Constants.SUCCESS,response.getStatus());
		Assert.assertEquals(ResponseMessage.USER_RETRIEVAL_SUCCESS,response.getMessage());
		Assert.assertEquals(ResponseCodes.USER_RETRIEVAL_SUCCESS,response.getCode());		
	}

}
