package com.revature.librarymanagement.service;

import java.util.List;

import com.revature.librarymanagement.dto.BookDto;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.model.Book;

public interface BookService {
	/**
	 * function to insert a book
	 * 
	 * @param bookDto--object of BookDto
	 * @return --it will return a message as added successfully
	 * @throws MethodArgumentNotValidException 
	 */

	public String addBook(BookDto bookDto) throws MethodArgumentNotValidException;

	/**
	 * function to update a book
	 * 
	 * @param bookDto--object of BookDto
	 * @return --it will return a message as updated successfully
	 */
	public String updateBook(BookDto bookDto);

	/**
	 * 
	 * @param bookId--based on bookId it will perform delete
	 * @return--it will return a message as deleted successfully
	 */
	public String deleteBook(Long bookId);

	/**
	 * 
	 * @param bookId--based on bookId it will perform function getBookById
	 * @return--return the object of Book repective to the bookId
	 */
	public Book getBookById(Long bookId);

	/**
	 * 
	 * @param bookId--based on bookId it will check existence of book
	 * @return--return boolean value true or false
	 */
	public boolean isBookExists(Long bookId);

	/**
	 * 
	 * @param bookName--based on bookName it will perform function getBookByName
	 * @return -- return the object of Book repective to the bookName
	 */
	public List<Book> getBookByName(String bookName);

	/**
	 * 
	 * @param authorName--based on authorName it will perform function
	 *                          getBookByAuthor
	 * @return -- return the object of Book repective to the authorName
	 */
	public List<Book> getBookByAuthor(String authorName);

	/**
	 * 
	 * @param genre--based on genre it will perform function getBookByGenre
	 * @return -- return the object of Book repective to the genre
	 */
	public List<Book> getBookByGenre(String genre);

	/**
	 * 
	 * @return-- it will return all the books from the books table
	 */
	public List<Book> getAllBooks();

	/**
	 * 
	 * @param isbn--based on isbn it will perform function getBookByISBN
	 * @return -- return the object of Book repective to the isbn
	 */
	public Book getBookByISBN(Long isbn);

	/**
	 * 
	 * @param publisher
	 * @return-book based on publisher given
	 */
	public List<Book> getBookByPublisher(String publisher);

	/**
	 * it will update the status of the book
	 * 
	 * @param bookId
	 * @param status
	 * @return-book status updated message
	 */
	public String updateBookStatus(Long bookId, String status);

	/**
	 * 
	 * @param value-by passing value we can search the book related to the value it
	 *                 may be bookname,authorname publishername,genre
	 * @return-list of books which matched with the value
	 */
	public List<Book> searchBooks(String value);

}
