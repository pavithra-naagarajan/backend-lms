package com.revature.librarymanagement.service.impl;

import java.util.Date;
import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.librarymanagement.dao.IssueBookDAO;
import com.revature.librarymanagement.dao.UserDAO;
import com.revature.librarymanagement.dto.IssueBookDto;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.exception.NullValueException;
import com.revature.librarymanagement.mapper.IssueBookMapper;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.IssueBookService;

@Service
public class IssueBookServiceImpl implements IssueBookService {
	private static final Logger logger = LogManager.getLogger(IssueBookServiceImpl.class);

	@Autowired
	private IssueBookDAO issueBookDAO;
	@Autowired
	private UserDAO userDAO;

	@Override
	public String issueBook(IssueBookDto issueBookDto, int numberOfDays) throws MethodArgumentNotValidException {
		logger.info("Entering issue book Function");

		IssueBook issueBook = IssueBookMapper.dtoToEntity(issueBookDto);
		Long issueId = issueBook.getIssueId();

		if (issueBookDAO.isIssuedDetailsExists(issueId))
			throw new DuplicateIdException(DUPLICATE_ISSUEDBOOK);
		try {
		return issueBookDAO.issueBook(issueBook, numberOfDays);}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
			throw new MethodArgumentNotValidException(VALIDATION_FAIL);
		}

	}

	@Override
	public List<IssueBook> getAllIssuedBooks() {
		logger.info("Entering get issued books Function");

		List<IssueBook> issuedBooks = issueBookDAO.getIssuedBooks();
		if (CollectionUtils.isEmpty(issuedBooks))
			throw new NullValueException(NO_RECORDS);
		return issuedBooks;

	}

	@Override
	public IssueBook getDetailsByIssueId(Long issueId) {
		logger.info("Entering get Details By IssueId Function");

		if (issueBookDAO.isIssuedDetailsExists(issueId))
			return issueBookDAO.getDetailsByIssueId(issueId);

		throw new IdNotFoundException(ID_NOT_FOUND);
	}

	@Override
	public String updateIssuedBook(IssueBookDto issueBookDto) {
		logger.info("Entering update issued book Function");

		IssueBook issueBook = IssueBookMapper.dtoToEntity(issueBookDto);
		Long issueId = issueBook.getIssueId();
		if (issueBookDAO.isIssuedDetailsExists(issueId))
			return issueBookDAO.updateIssuedBook(issueBook);

		throw new IdNotFoundException(NOT_FOUND_TOUPDATE);
	}

	@Override
	public String deleteIssuedBook(long issueId) {
		logger.info("Entering delete Details By IssueId Function");

		if (issueBookDAO.isIssuedDetailsExists(issueId))
			return issueBookDAO.deleteIssuedBook(issueId);

		throw new IdNotFoundException(NOT_FOUND_TODELETE);
	}

	@Override
	public boolean isIssuedDetailsExists(Long issueId) {
		logger.info("Entering isIssuedDetailsExists Function");

		return issueBookDAO.isIssuedDetailsExists(issueId);
	}

	@Override
	public List<IssueBook> getDetailsByUserId(Long userId) {
		User user = userDAO.getUserById(userId);
		logger.info("Entering get Details By UserId function");
		
		List<IssueBook> issuedBooks=issueBookDAO.getDetailsByUserId(user);
		if (CollectionUtils.isEmpty(issuedBooks))
			throw new NullValueException(NO_RECORDS);
		return issuedBooks;
	}

	@Override
	public IssueBook getDetailsByBookId(Book book) {
		logger.info("Entering get Details By bookId function");

		IssueBook issuedBook = issueBookDAO.getDetailsByBookId(book);
		if (issuedBook == null)
			throw new NullValueException(NO_RECORDS);
		return issuedBook;
	}

	@Override
	public List<IssueBook> getDetailsByIssueDate(Date issueDate) {
		logger.info("Entering get Details By issueDate function");

		List<IssueBook> issuedBooks=issueBookDAO.getDetailsByIssueDate(issueDate);
		if (CollectionUtils.isEmpty(issuedBooks))
			throw new NullValueException(NO_RECORDS);
		return issuedBooks;
	}

	@Override
	public List<IssueBook> getDetailsByDueDate(Date dueDate) {
		logger.info("Entering get Details By dueDate function");
		
		List<IssueBook> issuedBooks=issueBookDAO.getDetailsByDueDate(dueDate);
		if (CollectionUtils.isEmpty(issuedBooks))
			throw new NullValueException(NO_RECORDS);
		return issuedBooks;
	}

	@Override
	public String updateFineAmount(Long issueId) {
		logger.info("Entering updateFineAmount function");

		IssueBook issuedDetails = issueBookDAO.getDetailsByIssueId(issueId);
		if (issuedDetails != null)
			return issueBookDAO.updateFineAmount(issueId);
		throw new IdNotFoundException(ID_NOT_FOUND);

	}

	@Override
	public String updateDueDate(Long issueId) {
		logger.info("Entering updateDueDate function");

		IssueBook issuedDetails = issueBookDAO.getDetailsByIssueId(issueId);
		if (issuedDetails != null)
			return issueBookDAO.updateDueDate(issueId);
		throw new IdNotFoundException(ID_NOT_FOUND);
	}

}
