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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.librarymanagement.dto.RequestBookDto;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.response.HttpResponseStatus;
import com.revature.librarymanagement.service.BookService;
import com.revature.librarymanagement.service.RequestBookService;
import com.revature.librarymanagement.service.UserService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("requestbook")
public class RequestBookController {
	private static final Logger logger = LogManager.getLogger(RequestBookController.class);

	@Autowired
	RequestBookService requestBookService;
	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	/**
	 * it will add request for book
	 * 
	 * @param requestBookDto
	 * @throws MethodArgumentNotValidException 
	 * @return-added request message
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addRequestBook(@Valid @RequestBody RequestBookDto requestBookDto) throws MethodArgumentNotValidException {
		logger.info("Entering add requested book Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.CREATED.value(), requestBookService.addRequestBook(requestBookDto)),
				HttpStatus.CREATED);

	}

	/**
	 * 
	 * @return-all the requested books
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllRequestedBooks() {
		logger.info("Entering get all requested book Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, requestBookService.getAllRequestedBooks()),
				HttpStatus.OK);

	}

	/**
	 * delete requested book based on request id
	 * 
	 * @param requestId
	 * @return-deleted message
	 */

	@DeleteMapping("/{requestId}")
	public ResponseEntity<HttpResponseStatus> deleteRequestedBook(@PathVariable("requestId") Long requestId) {
		logger.info("Entering delete requested book Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), requestBookService.deleteRequestedBook(requestId)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param requestId
	 * @return-issue details based on request id
	 */
	@GetMapping("/request/{requestId}")
	public ResponseEntity<HttpResponseStatus> getDetailsByRequestId(@PathVariable("requestId") Long requestId) {
		logger.info("Entering get requested book by Id Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				requestBookService.getDetailsByRequestId(requestId)), HttpStatus.OK);

	}

}
