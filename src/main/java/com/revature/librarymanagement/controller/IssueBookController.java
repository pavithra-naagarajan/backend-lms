package com.revature.librarymanagement.controller;

import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

import java.io.ByteArrayInputStream;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.librarymanagement.dto.IssueBookDto;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.response.HttpResponseStatus;
import com.revature.librarymanagement.service.BookService;
import com.revature.librarymanagement.service.IssueBookService;
import com.revature.librarymanagement.service.UserService;
import com.revature.librarymanagement.util.PDFGenerator;

import org.springframework.http.MediaType;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("issuebook")
public class IssueBookController {

	private static final Logger logger = LogManager.getLogger(IssueBookController.class);

	@Autowired
	IssueBookService issueBookService;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	/**
	 * function to insert a issuedbook and details
	 * 
	 * @param issueBookDto--object of IssueBookDto
	 * @return --it will return a message as issued successfully
	 * @throws MethodArgumentNotValidException 
	 */
	@PostMapping("/{numberOfDays}")
	public ResponseEntity<HttpResponseStatus> issueBook(@Valid @RequestBody IssueBookDto issueBookDto,
			@PathVariable int numberOfDays) throws MethodArgumentNotValidException {
		logger.info("Entering issue book Function");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(),
				issueBookService.issueBook(issueBookDto, numberOfDays)), HttpStatus.CREATED);

	}

	/**
	 * 
	 * @return--it will return all the issued books with user id and book id
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllIssuedBooks() {
		logger.info("Entering Get all issued books Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, issueBookService.getAllIssuedBooks()),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param issueId--based on that we can fetch the issued details
	 * @return--it will return the issued details respective to the issueId
	 */

	@GetMapping("/issue/{issueId}")
	public ResponseEntity<HttpResponseStatus> getDetailsByIssueId(@PathVariable("issueId") Long issueId) {
		logger.info("Entering Get issued book details By issue Id Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				issueBookService.getDetailsByIssueId(issueId)), HttpStatus.OK);

	}

	/**
	 * 
	 * @param userId--based on userId we can find the issued details
	 * @return--it will return issued book details respective to the user
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<HttpResponseStatus> getDetailsByUserId(@PathVariable("userId") Long userId) {
		logger.info("Entering Get issued book details By user Id Function");

		
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
					issueBookService.getDetailsByUserId(userId)), HttpStatus.OK);
		

	}

	/**
	 * 
	 * @param bookId--based on bookId we can find the issued details
	 * @return--it will return issued book details respective to the book
	 */
	@GetMapping("/book/{bookId}")
	public ResponseEntity<HttpResponseStatus> getDetailsByBookId(@PathVariable("bookId") Long bookId) {
		logger.info("Entering Get issued book details By book Id Function");

		Book book = bookService.getBookById(bookId);
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, issueBookService.getDetailsByBookId(book)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param dueDate--based on dueDate we can find the issued details
	 * @return--it will return issued book details respective to the dueDate
	 */

	@GetMapping(value = "/duedate")
	public ResponseEntity<HttpResponseStatus> getDetailsByDueDate(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dueDate) {
		logger.info("Entering Get issued book details By duedate Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				issueBookService.getDetailsByDueDate(dueDate)), HttpStatus.OK);

	}

	/**
	 * 
	 * @param issueDate--based on issueDate we can find the issued details
	 * @return--it will return issued book details respective to the issueDate
	 */

	@GetMapping(value = "/issuedate")
	public ResponseEntity<HttpResponseStatus> getDetailsByIssueDate(
			@RequestParam("issueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date issueDate) {
		logger.info("Entering Get issued book details By issuedate Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				issueBookService.getDetailsByIssueDate(issueDate)), HttpStatus.OK);

	}

	/**
	 * function to update a issuedbook and details
	 * 
	 * @param issueBookDto--object of IssueBookDto
	 * @return --it will return a message as update successfully
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateIssuedBook(@RequestBody IssueBookDto issueBookDto) {

		logger.info("Entering update issued book details Function");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), issueBookService.updateIssuedBook(issueBookDto)),
				HttpStatus.OK);
	}

	/**
	 * function to delete a issuedbook and details
	 * 
	 * @param issueId--based on issued Id it will perform delete
	 * @return --it will return a message as delete successfully
	 */
	@DeleteMapping("/{issueId}")
	public ResponseEntity<HttpResponseStatus> deleteIssuedBook(@PathVariable("issueId") Long issueId) {

		logger.info("Entering delete issued book details By issue Id Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), issueBookService.deleteIssuedBook(issueId)),
				HttpStatus.OK);

	}

	/**
	 * update fine based on issueId
	 * 
	 * @param issueId
	 * @return-fine updated message
	 */
	@PutMapping("/updatefine/{issueId}")
	public ResponseEntity<HttpResponseStatus> updateFineAmount(@PathVariable Long issueId) {
		logger.info("Entering update fine amount of issued book details By issue Id Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), issueBookService.updateFineAmount(issueId)),
				HttpStatus.OK);

	}

	/**
	 * update due date while renew the book
	 * 
	 * @param issueId
	 * @return-due date updated message
	 */

	@PutMapping("/updateduedate/{issueId}")
	public ResponseEntity<HttpResponseStatus> updateDueDate(@PathVariable Long issueId) {
		logger.info("Entering update due date of issued book details By issue Id Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), issueBookService.updateDueDate(issueId)), HttpStatus.OK);

	}

	@GetMapping(value = "/generatepdf/{issueId}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> fineReport(@PathVariable("issueId") Long issueId) {
		IssueBook issuedDetails = issueBookService.getDetailsByIssueId(issueId);

		ByteArrayInputStream bis = PDFGenerator.userFineAmountReport(issuedDetails);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=salesReport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
}
