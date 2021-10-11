package com.revature.librarymanagement.dao.impl;

import java.time.LocalDateTime;

import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.librarymanagement.dao.IssueBookDAO;
import com.revature.librarymanagement.exception.DatabaseException;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.BookService;
import com.revature.librarymanagement.service.UserService;
import com.revature.librarymanagement.util.AsynchronousMailSender;

@Repository
public class IssueBookDAOImpl implements IssueBookDAO {

	private static final Logger logger = LogManager.getLogger(IssueBookDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	private static final String GET_ALL_ISSUEDBOOKS = "select i from IssueBook i";
	private static final String GET_BY_USERID = "select i from IssueBook i where i.user=?1";
	private static final String GET_BY_BOOKID = "select i from IssueBook i where i.book=?1";
	private static final String GET_BY_ISSUEDATE = "select i from IssueBook i where i.issueDate=?1";
	private static final String GET_BY_DUEDATE = "select i from IssueBook i where i.dueDate=?1";

	@Transactional
	@Override
	public String issueBook(IssueBook issueBook, int numberOfDays) {
		logger.info("Entering issue book Function");

		Long issueId = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Long bookId = issueBook.getBook().getBookId();
			bookService.updateBookStatus(bookId, "Issued");
			Long userId = issueBook.getUser().getUserId();
			userService.updateUserStatus(userId, "Active");

			User user = issueBook.getUser();
			Book book = issueBook.getBook();

			// set due Date
			Date date = issueBook.getIssueDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			// manipulate date
			cal.add(Calendar.DATE, numberOfDays);
			Date dueDate = cal.getTime();

			issueBook.setDueDate(dueDate);

			session.save(issueBook);
			issueId = issueBook.getIssueId();
			AsynchronousMailSender.sendMail(user.getMailId(), "Regarding Book Issue :",
					"Hi, " + user.getFirstName() + "\nThe book which you have requested is issued successfully!"
							+ "\nBook Name :" + book.getBookName() + "\nAuthor Name :" + book.getAuthorName()
							+ "\nIssue Date :" + issueBook.getIssueDate() + "\nDue Date :" + issueBook.getDueDate()
							+ "\nThank You.");
			return ISSUE_BOOK + issueId + " at " + localTime;

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_INSERT);
		}

	}

	@Override
	public List<IssueBook> getIssuedBooks() {
		logger.info("Entering get issued books Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			Query<IssueBook> query = session.createQuery(GET_ALL_ISSUEDBOOKS, IssueBook.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public IssueBook getDetailsByIssueId(Long issueId) {
		logger.info("Entering get Details By IssueId Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			return session.get(IssueBook.class, issueId);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional
	@Override
	public String updateIssuedBook(IssueBook issueBook) {
		logger.info("Entering update issued book Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(issueBook);
			return UPDATE_ISSUE;

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_UPDATE);
		}

	}

	@Transactional
	@Override
	public String deleteIssuedBook(long issueId) {
		logger.info("Entering delete Details By IssueId Function");

		IssueBook issuedDetails = getDetailsByIssueId(issueId);
		try {
			Session session = sessionFactory.getCurrentSession();
			Long bookId = issuedDetails.getBook().getBookId();
			bookService.updateBookStatus(bookId, "Available");
			session.save(issuedDetails);
			issueId = issuedDetails.getIssueId();

			session.delete(issuedDetails);
			return DELETE_ISSUE + issueId + " at " + localTime;

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_DELETE);
		}
	}

	@Override
	public boolean isIssuedDetailsExists(Long issueId) {
		logger.info("Entering isIssuedDetailsExists Function");
		try {
			Session session = sessionFactory.getCurrentSession();
			IssueBook issuedDetails = session.get(IssueBook.class, issueId);
			return (issuedDetails != null);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<IssueBook> getDetailsByUserId(User user) {
		logger.info("Entering get Details By UserId function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<IssueBook> resultList = session.createQuery(GET_BY_USERID, IssueBook.class).setParameter(1, user)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public IssueBook getDetailsByBookId(Book book) {
		logger.info("Entering get Details By bookId function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<IssueBook> resultList = session.createQuery(GET_BY_BOOKID, IssueBook.class).setParameter(1, book)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<IssueBook> getDetailsByIssueDate(Date issueDate) {
		logger.info("Entering get Details By issueDate function");

		try {
			Session session = sessionFactory.getCurrentSession();
			List<IssueBook> resultList = session.createQuery(GET_BY_ISSUEDATE, IssueBook.class)
					.setParameter(1, issueDate).getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<IssueBook> getDetailsByDueDate(Date dueDate) {
		logger.info("Entering get Details By dueDate function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<IssueBook> resultList = session.createQuery(GET_BY_DUEDATE, IssueBook.class).setParameter(1, dueDate)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional

	@Override
	public String updateFineAmount(Long issueId) {
		logger.info("Entering updateFineAmount function");

		try {

			Session session = sessionFactory.getCurrentSession();
			IssueBook issueDetails = getDetailsByIssueId(issueId);
			int fine = issueDetails.getFineAmount();
			Date due = issueDetails.getDueDate();
			Date now = new Date();
			if (now.getTime() < due.getTime()) {
				issueDetails.setFineAmount(fine);
			} else {
				long diffInMillies = Math.abs(now.getTime() - due.getTime());
				long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

				if (diff > 0 && diff < 15) {
					issueDetails.setFineAmount(fine + 20);
				} else if (diff > 15 && diff < 30) {
					issueDetails.setFineAmount(fine + 30);
				} else {
					issueDetails.setFineAmount(fine + 50);

				}
			}

			session.update(issueDetails);
			AsynchronousMailSender.sendMail(issueDetails.getUser().getMailId(), "Fine Payment :", "Hi, "
					+ issueDetails.getUser().getFirstName() + "\nYour fine is updated for the issued book."
					+ "\n Book Name :" + issueDetails.getBook().getBookName()
					+ "\nPlease be ensure that you have to renew the issued book within due date.\nCheck the fine Amount in your dashboard!"
					+ "\n\nThank You.");
			return UPDATE_FINE;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}

	@Transactional
	@Override
	public String updateDueDate(Long issueId) {
		logger.info("Entering updateDueDate function");
		try {

			Session session = sessionFactory.getCurrentSession();

			IssueBook issueDetails = getDetailsByIssueId(issueId);
			Date date = issueDetails.getDueDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			// manipulate date
			cal.add(Calendar.DATE, 15);
			Date dueDate = cal.getTime();

			issueDetails.setDueDate(dueDate);
			session.update(issueDetails);
			AsynchronousMailSender.sendMail(issueDetails.getUser().getMailId(), "Renewal success :",
					"Hi, " + issueDetails.getUser().getFirstName() + "\nYour due date is changed for the issued book."
							+ "\n Book Name :" + issueDetails.getBook().getBookName()
							+ "\nCheck it once again in your user dashboard!" + "\n\nThank You.");
			return UPDATE_DUEDATE;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new DatabaseException(ERROR_IN_UPDATE);
		}
	}
}
