package org.save.the.children.practicaltest.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.save.the.children.practicaltest.api.response.UserResponse;
import org.save.the.children.practicaltest.constant.Constants;
import org.save.the.children.practicaltest.constant.ResponseCodes;
import org.save.the.children.practicaltest.constant.ResponseMessage;
import org.save.the.children.practicaltest.cryptography.engine.CryptographyEngine;
import org.save.the.children.practicaltest.exception.SystemRuntimeException;
import org.save.the.children.practicaltest.model.User;
import org.save.the.children.practicaltest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = Logger.getLogger(UserServiceImpl.class.toString());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CryptographyEngine cryptographyEngine;

	@Override
	public UserResponse registerUser(User userEntity) {
		logger.info("Processing user registration");
		UserResponse userResponse = new UserResponse();
		try {
			userEntity.setTitle(cryptographyEngine.encryptData(userEntity.getTitle()));
			userEntity.setFirstName(cryptographyEngine.encryptData(userEntity.getFirstName()));
			userEntity.setLastName(cryptographyEngine.encryptData(userEntity.getLastName()));
			userEntity.setAddressLine1(cryptographyEngine.encryptData(userEntity.getAddressLine1()));
			userEntity.setAddressLine2(cryptographyEngine.encryptData(userEntity.getAddressLine2()));
			userEntity.setCity(cryptographyEngine.encryptData(userEntity.getCity()));
			userEntity.setCountry(cryptographyEngine.encryptData(userEntity.getCountry()));
			userEntity.setPostCode(cryptographyEngine.encryptData(userEntity.getPostCode()));
			userEntity.setIdentityNumber(cryptographyEngine.encryptData(userEntity.getIdentityNumber()));
			User user = userRepository.save(userEntity);

			userResponse.setUser(user);
			userResponse.setStatus(Constants.SUCCESS);
			userResponse.setCode(ResponseCodes.USER_REGISTER_SUCCESS);
			userResponse.setMessage(ResponseMessage.USER_REGISTER_SUCCESS);
			logger.info("User registration is successed");
		} catch (Exception e) {
			userResponse.setStatus(Constants.FAILURE);
			userResponse.setCode(ResponseCodes.USER_REGISTER_UN_SUCCESS);
			userResponse.setMessage(ResponseMessage.USER_REGISTER_UN_SUCCESS);
			userResponse.setMessageDescription(e.getMessage());
		}
		return userResponse;
	}

	@Override
	public UserResponse retreiveUserInfo(Long userId) {
		logger.info("Processing to retrieve user information");
		UserResponse userResponse = new UserResponse();
		try {
			Optional<User> user = userRepository.findById(userId);
			if (user.isPresent()) {
				logger.info("user is exist");
				User userObject = user.get();
				userObject.setTitle(cryptographyEngine.decryptData(userObject.getTitle()));
				userObject.setFirstName(cryptographyEngine.decryptData(userObject.getFirstName()));
				userObject.setLastName(cryptographyEngine.decryptData(userObject.getLastName()));
				userObject.setAddressLine1(cryptographyEngine.decryptData(userObject.getAddressLine1()));
				userObject.setAddressLine2(cryptographyEngine.decryptData(userObject.getAddressLine2()));
				userObject.setCity(cryptographyEngine.decryptData(userObject.getCity()));
				userObject.setCountry(cryptographyEngine.decryptData(userObject.getCountry()));
				userObject.setPostCode(cryptographyEngine.decryptData(userObject.getPostCode()));
				userObject.setIdentityNumber(cryptographyEngine.decryptData(userObject.getIdentityNumber()));

				userResponse.setUser(userObject);
				userResponse.setStatus(Constants.SUCCESS);
				userResponse.setCode(ResponseCodes.USER_RETRIEVAL_SUCCESS);
				userResponse.setMessage(ResponseMessage.USER_RETRIEVAL_SUCCESS);
				logger.info("User retreival is successed");
			} else {
				logger.info("Cannot find user information");
				throw new SystemRuntimeException(Constants.FAILURE, ResponseCodes.USER_NOT_EXIST,
						ResponseMessage.USER_NOT_EXIST);
			}
		} catch (Exception e) {
			userResponse.setStatus(Constants.FAILURE);
			userResponse.setCode(ResponseCodes.USER_RETRIEVE_ERROR);
			userResponse.setMessage(ResponseMessage.USER_RETRIEVE_ERROR);
			userResponse.setMessageDescription(e.getMessage());
		}
		return userResponse;
	}

}
