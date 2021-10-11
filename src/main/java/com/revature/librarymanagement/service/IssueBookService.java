package com.revature.librarymanagement.service;

import java.util.Date;

import java.util.List;

import com.revature.librarymanagement.dto.IssueBookDto;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;

public interface IssueBookService {

	/**
	 * function to insert a issuedbook and details
	 * 
	 * @param issueBookDto--object of IssueBookDto
	 * @return --it will return a message as issued successfully
	 * @throws MethodArgumentNotValidException 
	 */
	public String issueBook(IssueBookDto issueBookDto, int numberOfDays) throws MethodArgumentNotValidException;

	/**
	 * 
	 * @return--it will return all the issued books with user id and book id
	 */
	public List<IssueBook> getAllIssuedBooks();

	/**
	 * 
	 * @param issueId--based on that we can fetch the issued details
	 * @return--it will return the issued details respective to the issueId
	 */
	public IssueBook getDetailsByIssueId(Long issueId);

	/**
	 * function to update a issuedbook and details
	 * 
	 * @param issueBookDto--object of IssueBookDto
	 * @return --it will return a message as update successfully
	 */
	public String updateIssuedBook(IssueBookDto issueBookDto);

	/**
	 * function to delete a issuedbook and details
	 * 
	 * @param issueId--based on issued Id it will perform delete
	 * @return --it will return a message as delete successfully
	 */
	public String deleteIssuedBook(long issueId);

	/**
	 * 
	 * @param issueId--based on issueId it will check existence of issued details
	 * @return--return boolean value true or false
	 */
	public boolean isIssuedDetailsExists(Long issueId);

	/**
	 * 
	 * @param user--based on user we can find the issued details
	 * @return--it will return issued book details respective to the user
	 */
	public List<IssueBook> getDetailsByUserId(Long userId);

	/**
	 * 
	 * @param book--based on book we can find the issued details
	 * @return--it will return issued book details respective to the book
	 */
	public IssueBook getDetailsByBookId(Book book);

	/**
	 * 
	 * @param issueDate--based on issueDate we can find the issued details
	 * @return--it will return issued book details respective to the issueDate
	 */

	public List<IssueBook> getDetailsByIssueDate(Date issueDate);

	/**
	 * 
	 * @param dueDate--based on dueDate we can find the issued details
	 * @return--it will return issued book details respective to the dueDate
	 */

	public List<IssueBook> getDetailsByDueDate(Date dueDate);

	/**
	 * update fine based on issueId
	 * 
	 * @param issueId
	 * @return-fine updated message
	 */
	public String updateFineAmount(Long issueId);

	/**
	 * update due date while renew the book
	 * 
	 * @param issueId
	 * @return-due date updated message
	 */
	public String updateDueDate(Long issueId);
}
