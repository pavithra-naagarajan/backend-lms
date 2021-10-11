package com.revature.librarymanagement.dao.impl;

import java.time.LocalDateTime;
import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.librarymanagement.dao.BookDAO;
import com.revature.librarymanagement.exception.DatabaseException;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.util.ISBNGenerator;

@Repository
public class BookDAOImpl implements BookDAO {
	private static final Logger logger = LogManager.getLogger(BookDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_BOOK_BY_NAME = "select b from Book b where b.bookName LIKE ?1";
	private static final String GET_BOOK_BY_AUTHORNAME = "select b from Book b where b.authorName LIKE?1";
	private static final String GET_BOOK_BY_GENRE = "select b from Book b where b.genre=?1";
	private static final String GET_BOOK_BY_PUBLISHER = "select b from Book b where b.publisher LIKE?1";
	private static final String GET_ALL_BOOKS = "select b from Book b";
	private static final String GET_BOOK_BY_ISBN = "select b from Book b where b.isbn=?1";

	private static final String SEARCH_BOOKS = "select b from Book b where b.bookName LIKE?1 OR b.authorName LIKE?2"
			+ "OR b.publisher LIKE?3 OR b.status LIKE?4 OR b.genre LIKE?5";

	@Transactional
	@Override
	public String addBook(Book book) {
		logger.info("Entering add book Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			book.setIsbn(ISBNGenerator.generateISBN());
			book.setStatus("Available");
			book.setCreatedOn(new Date());
			session.save(book);
			logger.info("Book added");
			return INSERT_BOOK  + " at " + localTime;

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_INSERT);
		}

	}

	@Transactional
	@Override
	public String updateBook(Book book) {
		logger.info("Entering update book Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			book.setUpdatedOn(new Date());

			session.merge(book);
			logger.info("Book updated");
			return UPDATE_BOOK;

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_UPDATE);
		}

	}

	@Transactional
	@Override
	public String deleteBook(Long bookId) {
		logger.info("Entering delete book By Id Function");

		Book book = getBookById(bookId);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(book);
			logger.info("Book deleted");
			return DELETE_BOOK + bookId + " at " + localTime;

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_DELETE);
		}

	}

	@Override
	public Book getBookById(Long bookId) {
		logger.info("Entering get book By Id Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Book.class, bookId);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public boolean isBookExists(Long bookId) {
		logger.info("Entering is book exists Function");
		try {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.get(Book.class, bookId);
		return (book != null);
		}catch(Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<Book> getBookByName(String bookName) {
		logger.info("Entering get book By name Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(GET_BOOK_BY_NAME, Book.class)
					.setParameter(1, "%" + bookName + "%").getResultList();
			return ((resultList).isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<Book> getBookByAuthor(String authorName) {
		logger.info("Entering get book By author name Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(GET_BOOK_BY_AUTHORNAME, Book.class)
					.setParameter(1, "%" + authorName + "%").getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<Book> getBookByGenre(String genre) {
		logger.info("Entering get book By genre name Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(GET_BOOK_BY_GENRE, Book.class).setParameter(1, genre)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<Book> getBookByPublisher(String publisher) {
		logger.info("Entering get book By publisher name Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(GET_BOOK_BY_PUBLISHER, Book.class)
					.setParameter(1, "%" + publisher + "%").getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<Book> getAllBooks() {
		logger.info("Entering get all books Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			Query<Book> query = session.createQuery(GET_ALL_BOOKS, Book.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public Book getBookByISBN(Long isbn) {
		logger.info("Entering get book By ISBN Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(GET_BOOK_BY_ISBN, Book.class).setParameter(1, isbn)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional
	@Override
	public String updateBookStatus(Long bookId, String status) {
		logger.info("Entering update book status Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			Book book = getBookById(bookId);
			book.setStatus(status);
			session.merge(book);
			return UPDATE_BOOK_STATUS;

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public List<Book> searchBooks(String value) {
		logger.info("Entering search books name Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(SEARCH_BOOKS, Book.class).setParameter(1, "%" + value + "%")
					.setParameter(2, "%" + value + "%").setParameter(3, "%" + value + "%")
					.setParameter(4, "%" + value + "%").setParameter(5, "%" + value + "%").getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}
}
