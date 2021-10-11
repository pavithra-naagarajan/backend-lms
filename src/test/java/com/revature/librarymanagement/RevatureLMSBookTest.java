package com.revature.librarymanagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.librarymanagement.dao.BookDAO;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
class RevatureLMSBookTest {

	@Autowired
	private BookService bookService;

	@MockBean
	private BookDAO bookDAO;

	static final List<Book> BOOK_TEST_DATA = Stream.of(
			new Book(Long.valueOf(1), Long.valueOf(898778), "My world", "chetan bhagat", "Zara", "Comic", 1, 2,
					"Available"),
			new Book(Long.valueOf(2), Long.valueOf(898779), "My Life", "chetan bhagat", "Zara", "Autobiography", 1, 2,
					"Available"))
			.collect(Collectors.toList());

	static final List<Book> BOOK_TEST_DATA1 = Stream.of(new Book(Long.valueOf(1), Long.valueOf(898778), "My world",
			"chetan bhagat", "Zara", "Comic", 1, 2, "Available")).collect(Collectors.toList());;

	@Test
	void getAllBooksTest() {

		when(bookDAO.getAllBooks()).thenReturn(BOOK_TEST_DATA);

		assertEquals(2, bookService.getAllBooks().size());
	}

	@Test
	void getBookByIdTest() {
		Book book = new Book(Long.valueOf(1), Long.valueOf(898778), "My world", "chetan bhagat", "Zara", "Comic", 1, 2,
				"Available");
		when(bookDAO.getBookById(Long.valueOf(1))).thenReturn(book);
		assertEquals(book, bookDAO.getBookById(Long.valueOf(1)));
	}

	@Test
	void testGetBookByName() {
		String bookName = "My world";
		when(bookDAO.getBookByName(bookName)).thenReturn(BOOK_TEST_DATA1);
		assertEquals(1, bookService.getBookByName(bookName).size());
	}

	@Test
	void testGetBookByAuthor() {
		String authorName = "chetan bhagat";
		when(bookDAO.getBookByAuthor(authorName)).thenReturn(BOOK_TEST_DATA1);
		assertEquals(1, bookService.getBookByAuthor(authorName).size());
	}

	@Test
	void testGetBookByGenre() {
		String genre = "Comic";
		when(bookDAO.getBookByGenre(genre)).thenReturn(BOOK_TEST_DATA1);
		assertEquals(1, bookService.getBookByGenre(genre).size());
	}

	@Test
	void testGetBookByPublisher() {
		String publisher = "Zara";
		when(bookDAO.getBookByPublisher(publisher)).thenReturn(BOOK_TEST_DATA1);
		assertEquals(1, bookService.getBookByPublisher(publisher).size());
	}

	@Test
	void deleteBookTest() {

		Book book = new Book(Long.valueOf(1), Long.valueOf(898778), "My world", "chetan bhagat", "Zara", "Comic", 1, 2,
				"Available");
		bookDAO.deleteBook(book.getBookId());
		verify(bookDAO, times(1)).deleteBook(book.getBookId());
	}

	@Test
	void addBookTest() {
		Book book = new Book(Long.valueOf(1), Long.valueOf(898778), "My world", "chetan bhagat", "Zara", "Comic", 1, 2,
				"Available");

		bookDAO.addBook(book);
		verify(bookDAO, times(1)).addBook(book);
	}

	@Test
	void updateBookTest() {
		Book book = new Book(Long.valueOf(1), Long.valueOf(898778), "My world", "chetan bhagat", "Zara", "Comic", 1, 2,
				"Available");

		bookDAO.updateBook(book);
		verify(bookDAO, times(1)).updateBook(book);
	}

}
