
package com.revature.librarymanagement.controller;

import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.librarymanagement.dto.UserDto;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.response.HttpResponseStatus;
import com.revature.librarymanagement.service.UserService;

@CrossOrigin(origins = "*")

@RestController

@RequestMapping("user")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/**
	 * based on mailId and password it will perform login function whether the user
	 * is valid user or not
	 * 
	 * @param mailId
	 * @param password
	 * @return--it will return user
	 */

	@GetMapping("login/{mailId}/{password}")
	public ResponseEntity<HttpResponseStatus> userLogin(@PathVariable("mailId") String mailId,
			@PathVariable("password") String password) {
		logger.info("Entering user login Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, userService.userLogin(mailId, password)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param userId--based on userId it will perform function getUserById
	 * @throws InputMismatchValidation 
	 * @return--return the object of User repective to the userId
	 */
	@GetMapping("{userId}")
	public ResponseEntity<HttpResponseStatus> getUserById(@PathVariable("userId") Long userId) {
		logger.info("Entering get user by Id Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, userService.getUserById(userId)),
				HttpStatus.OK);

	}

	/**
	 * function to insert a user
	 * 
	 * @param userDto--Object of UserDto
	 * @throws MethodArgumentNotValidException 
	 * @return--it will return a message as added successfully
	 */

	@PostMapping
	public ResponseEntity<HttpResponseStatus> addUser(@Valid @RequestBody UserDto userDto) throws MethodArgumentNotValidException {
		logger.info("Entering add user Function");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(), userService.addUser(userDto)),
				HttpStatus.CREATED);

	}

	/**
	 * function to update a user
	 * 
	 * @param userDto--object of UserDto
	 * @return --it will return a message as updated successfully
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateUser(@RequestBody UserDto userDto) {
		logger.info("Entering update user Function");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), userService.updateUser(userDto)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param userId--based on userId it will perform delete
	 * @return--it will return a message as deleted successfully
	 */

	@DeleteMapping("/{userId}")
	public ResponseEntity<HttpResponseStatus> deleteUser(@PathVariable("userId") Long userId) {
		logger.info("Entering delete user Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), userService.deleteUserById(userId)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param name
	 * @throws InputMismatchValidation 
	 * @return--it will return the user based on firstname and lastname
	 */

	@GetMapping("/name/{name}")
	public ResponseEntity<HttpResponseStatus> getUserByFirstAndLastName(@PathVariable String name){
		logger.info("Entering get user by name Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				userService.getUserByFirstAndLastName(name)), HttpStatus.OK);

	}

	/**
	 * 
	 * @param userRole
	 * @return--it will return the list of users based on role
	 */

	@GetMapping("/role/{userRole}")
	public ResponseEntity<HttpResponseStatus> getUserByRole(@PathVariable String userRole) {
		logger.info("Entering get user by role Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, userService.getUserByRole(userRole)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param mobileNumber
	 * @return--it will return the user based on mobile number
	 */

	@GetMapping("/mobile/{mobileNumber}")
	public ResponseEntity<HttpResponseStatus> getUserByMobileNumber(@PathVariable String mobileNumber) {
		logger.info("Entering get user by mobile number Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				userService.getUserByMobileNumber(mobileNumber)), HttpStatus.OK);

	}

	/**
	 * 
	 * @param mailId
	 * @return--it will return the user based on mailId
	 */
	@GetMapping("/mail/{mailId}")
	public ResponseEntity<HttpResponseStatus> getUserByMailId(@PathVariable String mailId) {
		logger.info("Entering get user by mailId Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, userService.getUserByMailId(mailId)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @return--it will return list of users without any criteria
	 */

	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllUsers() {
		logger.info("Entering get all users Function");

	
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, userService.getAllUsers()),
					HttpStatus.OK);
		

	}

	/**
	 * it will generate password
	 * 
	 * @param mailId
	 * @return-new password
	 */
	@PutMapping("/forgotpassword/{mailId}")
	public ResponseEntity<HttpResponseStatus> forgotPassword(@PathVariable String mailId) {
		logger.info("Entering forgot password Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, userService.forgotPassword(mailId)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param value-by passing value we can search the book related to the value it
	 *                 may be name,mobilenumber mailId,userRole
	 * @return-list of users which matched with the value
	 */
	@GetMapping("/searchusers/{value}")
	public ResponseEntity<HttpResponseStatus> searchUsers(@PathVariable String value) {
		logger.info("Entering Search Users Function");
		
			return new ResponseEntity<>(

					new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, userService.searchUsers(value)),
					HttpStatus.OK);
		

	}

}
