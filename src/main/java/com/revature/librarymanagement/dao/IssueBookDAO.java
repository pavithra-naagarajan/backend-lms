package com.revature.librarymanagement.dao;

import java.util.Date;
import java.util.List;

import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;

public interface IssueBookDAO {
	public String issueBook(IssueBook issueBook, int numberOfDays);

	public List<IssueBook> getIssuedBooks();

	public IssueBook getDetailsByIssueId(Long issueId);

	public String updateIssuedBook(IssueBook issueBook);

	public String deleteIssuedBook(long issueId);

	public boolean isIssuedDetailsExists(Long issueId);

	public List<IssueBook> getDetailsByUserId(User user);

	public IssueBook getDetailsByBookId(Book book);

	public List<IssueBook> getDetailsByIssueDate(Date issueDate);

	public List<IssueBook> getDetailsByDueDate(Date dueDate);

	public String updateFineAmount(Long issueId);

	public String updateDueDate(Long issueId);

}
