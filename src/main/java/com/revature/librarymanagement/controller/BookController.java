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

import com.revature.librarymanagement.dto.BookDto;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.response.HttpResponseStatus;
import com.revature.librarymanagement.service.BookService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("book")
public class BookController {

	private static final Logger logger = LogManager.getLogger(BookController.class);

	@Autowired
	BookService bookService;

	/**
	 * 
	 * @param bookId--based on bookId it will perform function getBookById
	 * @return--return the object of Book repective to the bookId
	 */

	@GetMapping("{bookId}")
	public ResponseEntity<HttpResponseStatus> getBookById(@PathVariable("bookId") Long bookId) {
		logger.info("Entering Get Book By Id Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, bookService.getBookById(bookId)),
				HttpStatus.OK);

	}

	/**
	 * function to insert a book
	 * 
	 * @param bookDto--object of BookDto
	 * @return --it will return a message as added successfully
	 * @throws MethodArgumentNotValidException 
	 */

	@PostMapping
	public ResponseEntity<HttpResponseStatus> addBook(@Valid @RequestBody BookDto bookDto) throws MethodArgumentNotValidException {
		logger.info("Entering Add Book Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(), bookService.addBook(bookDto)),
				HttpStatus.CREATED);

	}

	/**
	 * function to update a book
	 * 
	 * @param bookDto--object of BookDto
	 * @return --it will return a message as updated successfully
	 */

	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateBook(@RequestBody BookDto bookDto) {

		logger.info("Entering Update Book Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), bookService.updateBook(bookDto)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param bookId--based on bookId it will perform delete
	 * @return--it will return a message as deleted successfully
	 */
	@DeleteMapping("/{bookId}")
	public ResponseEntity<HttpResponseStatus> deleteBook(@PathVariable("bookId") Long bookId) {
		logger.info("Entering Delete Book Function");
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), bookService.deleteBook(bookId)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param bookName--based on bookName it will perform function getBookByName
	 * @return -- return the object of Book repective to the bookName
	 */
	@GetMapping("/bookname/{bookName}")
	public ResponseEntity<HttpResponseStatus> getBookByName(@PathVariable String bookName) {
		logger.info("Entering Get Book By Name Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, bookService.getBookByName(bookName)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param authorName--based on authorName it will perform function
	 *                          getBookByAuthor
	 * @return -- return the object of Book repective to the authorName
	 */

	@GetMapping("/author/{authorName}")
	public ResponseEntity<HttpResponseStatus> getBookByAuthor(@PathVariable String authorName) {
		logger.info("Entering Get Book By Author Name Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, bookService.getBookByAuthor(authorName)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param genre--based on genre it will perform function getBookByGenre
	 * @return -- return the object of Book repective to the genre
	 */
	@GetMapping("/genre/{genre}")
	public ResponseEntity<HttpResponseStatus> getBookByGenre(@PathVariable String genre) {
		logger.info("Entering Get Book By genre Name Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, bookService.getBookByGenre(genre)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param publisher
	 * @return-book based on publisher given
	 */
	@GetMapping("/publisher/{publisher}")
	public ResponseEntity<HttpResponseStatus> getBookByPublisher(@PathVariable String publisher) {
		logger.info("Entering Get Book By publisher Name Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, bookService.getBookByPublisher(publisher)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @param isbn--based on isbn it will perform function getBookByISBN
	 * @return -- return the object of Book repective to the isbn
	 */
	@GetMapping("/isbn/{isbn}")
	public ResponseEntity<HttpResponseStatus> getBookByISBN(@PathVariable Long isbn) {
		logger.info("Entering Get Book By ISBN Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, bookService.getBookByISBN(isbn)),
				HttpStatus.OK);

	}

	/**
	 * 
	 * @return-- it will return all the books from the books table
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllBooks() {
		logger.info("Entering Get All Books Function");
		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, bookService.getAllBooks()), HttpStatus.OK);

	}

	/**
	 * 
	 * @param value-by passing value we can search the book related to the value it
	 *                 may be bookname,authorname publishername,genre
	 * @return-list of books which matched with the value
	 */
	@GetMapping("/searchbooks/{value}")
	public ResponseEntity<HttpResponseStatus> searchBooks(@PathVariable String value) {
		logger.info("Entering Search Books Function");
		
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, bookService.searchBooks(value)),
					HttpStatus.OK);
		
	}

}
