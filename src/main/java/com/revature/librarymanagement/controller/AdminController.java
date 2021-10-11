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

import com.revature.librarymanagement.dto.AdminDto;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.response.HttpResponseStatus;
import com.revature.librarymanagement.service.AdminService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	private static final Logger logger = LogManager.getLogger(AdminController.class);

	/**
	 * based on adminId and password it will perform login function
	 * 
	 * @param adminId
	 * @param adminPassword
	 * @return--it will return admin
	 */

	@GetMapping("login/{adminId}/{adminPassword}")
	public ResponseEntity<HttpResponseStatus> adminLogin(@PathVariable("adminId") Long adminId,
			@PathVariable("adminPassword") String adminPassword) {

		logger.info("Entering Admin Login Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				adminService.adminLogin(adminId, adminPassword)), HttpStatus.OK);

	}

	/**
	 * it will add admin
	 * 
	 * @param adminDto
	 * @throws MethodArgumentNotValidException 
	 * @return--it will return a message added successfully
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addAdmin(@Valid @RequestBody AdminDto adminDto) throws MethodArgumentNotValidException {
		logger.info("Entering Add Admin Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(), adminService.addAdmin(adminDto)),
				HttpStatus.CREATED);

	}

	/**
	 * it will update admin
	 * 
	 * @param adminDto
	 * @return--it will return a message updated successfully
	 */

	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateAdmin(@RequestBody AdminDto adminDto) {
		logger.info("Entering Update Admin Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), adminService.updateAdmin(adminDto)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param adminId--it will delete the admin based on adminId
	 * @return--it will return a message deleted successfully
	 */

	@DeleteMapping("/{adminId}")
	public ResponseEntity<HttpResponseStatus> deleteAdmin(@PathVariable("adminId") Long adminId) {
		logger.info("Entering Delete Admin Function");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), adminService.deleteAdminById(adminId)), HttpStatus.OK);

	}

	/**
	 * 
	 * @param adminRole--role of the admin
	 * @return--it will return the admin data respective to the role
	 */
	@GetMapping("role/{adminRole}")
	public ResponseEntity<HttpResponseStatus> getAdminByRole(@PathVariable String adminRole) {
		logger.info("Entering Get Admin By Role Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, adminService.getAdminByRole(adminRole)),
				HttpStatus.OK);

	}

	@GetMapping("name/{adminName}")
	public ResponseEntity<HttpResponseStatus> getAdminByName(@PathVariable String adminName) {
		logger.info("Entering Get Admin By Name Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, adminService.getAdminByName(adminName)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param adminId --this is the primary key of admin table.
	 * @return--by passing admin Id we can retrieve admin details respective to the
	 *             Id.
	 */

	@GetMapping("/{adminId}")
	public ResponseEntity<HttpResponseStatus> getAdminById(@PathVariable Long adminId) {
		logger.info("Entering Get Admin By Id Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, adminService.getAdminById(adminId)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @return--it will return the list of admins.
	 */

	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllAdmins() {
		logger.info("Entering Get All Admins Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, adminService.getAllAdmins()),
				HttpStatus.OK);

	}

}
