package org.save.the.children.practicaltest.controller;

import java.util.logging.Logger;

import org.save.the.children.practicaltest.api.response.UserResponse;
import org.save.the.children.practicaltest.exception.SystemRuntimeException;
import org.save.the.children.practicaltest.model.User;
import org.save.the.children.practicaltest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	Logger logger = Logger.getLogger(UserController.class.toString());

	@Autowired
	private UserService userService;

	@PostMapping
	public UserResponse registerUser(@RequestBody User userEntity) {
		UserResponse response = new UserResponse();
		try {
			logger.info("Start Execution of user registration");
			response = userService.registerUser(userEntity);
			logger.info("End Execution of user registration");
		} catch (SystemRuntimeException e) {
			logger.info("Eror in execution of user registration");
			response.setStatus(e.getStatus());
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
			response.setMessageDescription(e.getMessageDescription());
		}
		return response;
	}

	@GetMapping("/info")
	public UserResponse getUserInfo(@RequestParam("id") Long id) {
		UserResponse response = new UserResponse();
		try {
			logger.info("Start Execution of retrieve user information");
			response = userService.retreiveUserInfo(id);
			logger.info("End Execution of retrieve user information");
		} catch (SystemRuntimeException e) {
			logger.info("Eror in execution of user registration");
			response.setStatus(e.getStatus());
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
			response.setMessageDescription(e.getMessageDescription());
		}
		return response;
	}

}
