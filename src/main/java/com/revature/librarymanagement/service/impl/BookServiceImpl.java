package com.revature.librarymanagement.service.impl;

import java.util.List;

import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.librarymanagement.dao.BookDAO;
import com.revature.librarymanagement.dto.BookDto;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.exception.NullValueException;
import com.revature.librarymanagement.mapper.BookMapper;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

	@Autowired
	private BookDAO bookDAO;

	@Override
	public String addBook(BookDto bookDto) throws MethodArgumentNotValidException {
		logger.info("Entering add book Function");

		Book book = BookMapper.dtoToEntity(bookDto);
		Long bookId = book.getBookId();
		Long isbn = book.getIsbn();
		if (bookDAO.isBookExists(bookId))
			throw new DuplicateIdException(BOOK_EXIST_ALREADY);

		if (bookDAO.getBookByISBN(isbn) != null)

			throw new DuplicateIdException(DUPLICATE_BOOK);
		try {
			return bookDAO.addBook(book);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new MethodArgumentNotValidException(VALIDATION_FAIL);
		}
	}

	@Override
	public String updateBook(BookDto bookDto) {
		logger.info("Entering update book Function");

		Book book = BookMapper.dtoToEntity(bookDto);
		Long bookId = book.getBookId();
		if (bookDAO.isBookExists(bookId))
			return bookDAO.updateBook(book);
		throw new IdNotFoundException(NOT_FOUND_TOUPDATE);

	}

	@Override
	public String deleteBook(Long bookId) {
		logger.info("Entering delete book By Id Function");

		if (bookDAO.isBookExists(bookId))
			return bookDAO.deleteBook(bookId);
		throw new IdNotFoundException(NOT_FOUND_TODELETE);

	}

	@Override
	public Book getBookById(Long bookId) {
		logger.info("Entering get book By Id Function");

		if (bookDAO.isBookExists(bookId))
			return bookDAO.getBookById(bookId);
		throw new IdNotFoundException(ID_NOT_FOUND);

	}

	@Override
	public boolean isBookExists(Long bookId) {
		logger.info("Entering is book exists Function");

		return bookDAO.isBookExists(bookId);

	}

	@Override
	public List<Book> getBookByName(String bookName) {
		logger.info("Entering get book By name Function");

		List<Book> books = bookDAO.getBookByName(bookName);
		if (CollectionUtils.isEmpty(books))
			throw new NullValueException(NO_RECORDS);
		return books;

	}

	@Override
	public List<Book> getBookByAuthor(String authorName) {
		logger.info("Entering get book By author name Function");

		List<Book> books = bookDAO.getBookByAuthor(authorName);
		if (CollectionUtils.isEmpty(books))
			throw new NullValueException(NO_RECORDS);
		return books;
	}

	@Override
	public List<Book> getBookByGenre(String genre) {
		logger.info("Entering get book By genre name Function");

		List<Book> books = bookDAO.getBookByGenre(genre);
		if (CollectionUtils.isEmpty(books))
			throw new NullValueException(NO_RECORDS);
		return books;
	}

	@Override
	public List<Book> getAllBooks() {
		logger.info("Entering get all books Function");

		List<Book> books = bookDAO.getAllBooks();
		if (CollectionUtils.isEmpty(books))
			throw new NullValueException(NO_RECORDS);
		return books;

	}

	@Override
	public Book getBookByISBN(Long isbn) {
		logger.info("Entering get book By ISBN Function");

		Book book = bookDAO.getBookByISBN(isbn);
		if (book == null)
			throw new NullValueException(NO_RECORDS);
		return book;
	}

	@Override
	public List<Book> getBookByPublisher(String publisher) {
		logger.info("Entering get book By publisher name Function");

		List<Book> books = bookDAO.getBookByPublisher(publisher);
		if (CollectionUtils.isEmpty(books))
			throw new NullValueException(NO_RECORDS);
		return books;
	}

	@Override
	public String updateBookStatus(Long bookId, String status) {
		logger.info("Entering update book status Function");

		if (bookDAO.isBookExists(bookId))
			return bookDAO.updateBookStatus(bookId, status);
		throw new IdNotFoundException(ID_NOT_FOUND);

	}

	@Override
	public List<Book> searchBooks(String value) {
		logger.info("Entering update book status Function");
		List<Book> books = bookDAO.searchBooks(value);
		if (CollectionUtils.isEmpty(books))
			throw new NullValueException(NO_RECORDS);
		return books;
	}
}
