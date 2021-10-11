package com.revature.librarymanagement.dao.impl;

import java.time.LocalDateTime;
import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.librarymanagement.dao.RequestBookDAO;
import com.revature.librarymanagement.exception.DatabaseException;
import com.revature.librarymanagement.model.RequestBook;
import com.revature.librarymanagement.service.BookService;
import com.revature.librarymanagement.service.UserService;

@Repository
public class RequestBookDAOImpl implements RequestBookDAO {

	private static final Logger logger = LogManager.getLogger(RequestBookDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@Transactional
	@Override
	public String addRequestBook(RequestBook requestBook) {
		logger.info("Entering add request book Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			Long bookId = requestBook.getBook().getBookId();
			bookService.updateBookStatus(bookId, "Requested");
			Long userId = requestBook.getUser().getUserId();
			userService.updateUserStatus(userId, "Active");
			session.save(requestBook);
			return INSERT_REQUEST + localTime;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);

			throw new DatabaseException(ERROR_IN_INSERT);
		}

	}

	@Override
	public List<RequestBook> getAllRequestedBooks() {
		logger.info("Entering getAllRequestedBooks Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			Query<RequestBook> query = session.createQuery("select r from RequestBook r");
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);

			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional
	@Override
	public String deleteRequestedBook(Long requestId) {
		logger.info("Entering delete Requested Book Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			RequestBook requestedBook = getDetailsByRequestId(requestId);
			session.delete(requestedBook);
			return DELETE_REQUEST + localTime;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);

			throw new DatabaseException(ERROR_IN_DELETE);
		}
	}

	@Override
	public RequestBook getDetailsByRequestId(Long requestId) {
		logger.info("Entering get Details By RequestId Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			return session.get(RequestBook.class, requestId);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);

			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

}
