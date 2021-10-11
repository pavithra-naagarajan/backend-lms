
package com.revature.librarymanagement.service;

import java.util.List;


import com.revature.librarymanagement.dto.UserDto;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.model.User;

public interface UserService {

	/**
	 * function to insert a user
	 * 
	 * @param userDto--Object of UserDto
	 * @throws MethodArgumentNotValidException 
	 * @return--it will return a message as added successfully
	 */
	public String addUser(UserDto userDto) throws MethodArgumentNotValidException;

	/**
	 * function to update a user
	 * 
	 * @param userDto--object of UserDto
	 * @return --it will return a message as updated successfully
	 */
	public String updateUser(UserDto userDto);

	/**
	 * 
	 * @param userId--based on userId it will perform delete
	 * @return--it will return a message as deleted successfully
	 */
	public String deleteUserById(Long userId);

	/**
	 * 
	 * @param userId--based on userId it will perform function getUserById
	 * @return--return the object of User repective to the userId
	 */
	public User getUserById(Long userId);

	/**
	 * 
	 * @param name
	 * @return--it will return the user based on firstname and lastname
	 */
	public List<User> getUserByFirstAndLastName(String name);

	/**
	 * 
	 * @param mobileNumber
	 * @return--it will return the user based on mobile number
	 */
	public User getUserByMobileNumber(String mobileNumber);

	/**
	 * 
	 * @param mailId
	 * @return--it will return the user based on mailId
	 */
	public User getUserByMailId(String mailId);

	/**
	 * 
	 * @param userRole
	 * @return--it will return the list of users based on role
	 */
	public List<User> getUserByRole(String userRole);

	/**
	 * 
	 * @param userId--based on userId it will check the existence of the user
	 * @return--it will return boolean value true or false
	 */
	public boolean isUserExists(Long userId);

	/**
	 * 
	 * @return--it will return list of users without any criteria
	 */
	public List<User> getAllUsers();

	/**
	 * based on mailId and password it will perform login function whether the user
	 * is valid user or not
	 * 
	 * @param mailId
	 * @param password
	 * @return--it will return user
	 */
	public User userLogin(String mailId, String password);

	/**
	 * it will generate password
	 * 
	 * @param mailId
	 * @return-new password
	 */
	public String forgotPassword(String mailId);

	/**
	 * 
	 * @param value-by passing value we can search the book related to the value it
	 *                 may be name,mobilenumber mailId,userRole
	 * @return-list of users which matched with the value
	 */
	public List<User> searchUsers(String value);

	/**
	 * 
	 * @param userId
	 * @param status
	 * @return-it change the user status while adding the status will be Exist if
	 *            the user is a active member of a library management system if he
	 *            issued lot of books then the status of user can be considered as a
	 *            Active we cannot delete the user without proper reason and un
	 *            neccessarily.
	 */
	public String updateUserStatus(Long userId, String status);

}
