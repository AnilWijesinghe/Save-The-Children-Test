package org.save.the.children.practicaltest.service;

import org.save.the.children.practicaltest.api.response.UserResponse;
import org.save.the.children.practicaltest.model.User;

public interface UserService {

	/**
	 * user registration service
	 * 
	 * @param userEntity
	 * @return
	 */
	UserResponse registerUser(User userEntity);

	/**
	 * Retrieve user information using Id
	 * 
	 * @param userId
	 * @return
	 * @throws ResultNotFound
	 */
	UserResponse retreiveUserInfo(Long userId);

}
