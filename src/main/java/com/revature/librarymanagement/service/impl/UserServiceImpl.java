
package com.revature.librarymanagement.service.impl;



import java.util.List;

import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.revature.librarymanagement.dao.UserDAO;
import com.revature.librarymanagement.dto.UserDto;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.exception.NullValueException;
import com.revature.librarymanagement.mapper.UserMapper;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.UserService;
import com.revature.librarymanagement.util.PasswordGenerator;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;

	@Override
	public String addUser(UserDto userDto) throws MethodArgumentNotValidException {
		logger.info("Entering add user Function");

		User user = UserMapper.dtoToEntity(userDto);
		Long userId = user.getUserId();
		String mailId = user.getMailId();
		String mobileNumber = user.getMobileNumber();
		if (userDAO.isUserExists(userId)) {
			throw new DuplicateIdException(USER_EXIST_ALREADY);

		}

		if (userDAO.getUserByMailId(mailId) != null || userDAO.getUserByMobileNumber(mobileNumber) != null) {

			throw new DuplicateIdException(DUPLICATE_USER);

		}

		try {
			return userDAO.addUser(user);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new MethodArgumentNotValidException(VALIDATION_FAIL);
		}

	}

	@Override
	public String updateUser(UserDto userDto) {
		logger.info("Entering update user Function");

		User user = UserMapper.dtoToEntity(userDto);
		Long userId = user.getUserId();

		if (userDAO.isUserExists(userId))
			return userDAO.updateUser(user);

		else
			throw new IdNotFoundException(NOT_FOUND_TOUPDATE);

	}

	@Override
	public String deleteUserById(Long userId) {
		logger.info("Entering delete user Function");

		if (userDAO.isUserExists(userId))

			return userDAO.deleteUserById(userId);
		else

			throw new IdNotFoundException(NOT_FOUND_TODELETE);

	}

	@Override
	public User getUserById(Long userId){
		logger.info("Entering get User By Id Function");
		
		if (userDAO.isUserExists(userId))
			return userDAO.getUserById(userId);
		else

			throw new IdNotFoundException(ID_NOT_FOUND);

	}

	@Override
	public List<User> getUserByFirstAndLastName(String name) {
		logger.info("Entering get User By FirstAndLastName Function");

		List<User> users = userDAO.getUserByFirstAndLastName(name);

		if (CollectionUtils.isEmpty(users))
			throw new NullValueException(NO_RECORDS);

		return users;

	}

	@Override
	public User getUserByMobileNumber(String mobileNumber) {
		logger.info("Entering get User By mobile number Function");

		User user = userDAO.getUserByMobileNumber(mobileNumber);
		if (user == null)
			throw new NullValueException(NO_RECORDS);

		return user;
	}

	@Override
	public User getUserByMailId(String mailId) {
		logger.info("Entering get User By mailId Function");

		User user = userDAO.getUserByMailId(mailId);
		if (user == null)
			throw new NullValueException(NO_RECORDS);
		else
			return user;
	}

	@Override
	public List<User> getUserByRole(String userRole) {
		logger.info("Entering get User By user role Function");

		List<User> users = userDAO.getUserByRole(userRole);
		if (CollectionUtils.isEmpty(users))
			throw new NullValueException(NO_RECORDS);
		return users;
	}

	@Override
	public boolean isUserExists(Long userId) {
		logger.info("Entering is User Exists Function");

		return userDAO.isUserExists(userId);
	}

	@Override
	public List<User> getAllUsers() {
		logger.info("Entering get all users Function");

		List<User> users = userDAO.getAllUsers();
		if (CollectionUtils.isEmpty(users))
			throw new NullValueException(NO_RECORDS);
		return users;
	}

	@Override
	public User userLogin(String mailId, String password) {
		logger.info("Entering user login Function");

		User user = userDAO.userLogin(mailId, password);
		if (user == null)
			throw new NullValueException(NO_RECORDS);
		return user;
	}

	@Override
	public String forgotPassword(String mailId) {
		logger.info("Entering forgot password Function");

		User user = userDAO.getUserByMailId(mailId);
		if (user != null)
			return userDAO.forgotPassword(mailId, PasswordGenerator.generatePassword());

		else
			throw new IdNotFoundException(ID_NOT_FOUND);

	}

	@Override
	public List<User> searchUsers(String value) {
		logger.info("Entering search Users Function");
		List<User> users = userDAO.searchUsers(value);
		if (CollectionUtils.isEmpty(users))
			throw new NullValueException(NO_RECORDS);
		return users;

	}

	@Override
	public String updateUserStatus(Long userId, String status) {
		logger.info("Entering update user status Function");

		if (userDAO.isUserExists(userId))
			return userDAO.updateUserStatus(userId, status);
		else

			throw new IdNotFoundException(ID_NOT_FOUND);
	}

}
