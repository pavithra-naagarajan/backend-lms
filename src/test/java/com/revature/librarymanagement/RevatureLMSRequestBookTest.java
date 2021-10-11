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

import com.revature.librarymanagement.dao.RequestBookDAO;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.RequestBook;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.RequestBookService;

@RunWith(SpringRunner.class)
@SpringBootTest
class RevatureLMSRequestBookTest {

	@Autowired
	private RequestBookService requestBookService;

	@MockBean
	private RequestBookDAO requestBookDAO;

	static final User USER_DATA = new User(Long.valueOf(5), "pavithra", "Nagarajan", "pavi123", "pavi@gmail.com",
			"8525041821", "Student", 21, "Female", "chennai");

	static final Book BOOK_DATA = new Book(Long.valueOf(1), Long.valueOf(898778), "My world", "chetan bhagat", "Zara",
			"Comic", 1, 2, "Available");

	static final List<RequestBook> REQUESTBOOK_TEST_DATA = Stream
			.of(new RequestBook(Long.valueOf(1), 10, USER_DATA, BOOK_DATA)).collect(Collectors.toList());

	@Test
	void getAllRequestedDetailsTest() {

		when(requestBookDAO.getAllRequestedBooks()).thenReturn(REQUESTBOOK_TEST_DATA);

		assertEquals(1, requestBookService.getAllRequestedBooks().size());
	}

	@Test
	void getDetailsByRequestIdTest() {

		RequestBook requestedDetails = new RequestBook(Long.valueOf(1), 10, USER_DATA, BOOK_DATA);
		requestBookDAO.getDetailsByRequestId(Long.valueOf(1));
		when(requestBookDAO.getDetailsByRequestId(Long.valueOf(1))).thenReturn(requestedDetails);
		assertEquals(requestedDetails, requestBookDAO.getDetailsByRequestId(Long.valueOf(1)));

	}

	@Test
	void deleteRequestedBookTest() {

		RequestBook requestedDetails = new RequestBook(Long.valueOf(1), 10, USER_DATA, BOOK_DATA);
		requestBookDAO.deleteRequestedBook(requestedDetails.getRequestId());
		verify(requestBookDAO, times(1)).deleteRequestedBook(requestedDetails.getRequestId());
	}

	@Test
	void addRequestBookTest() {
		RequestBook requestedDetails = new RequestBook(Long.valueOf(1), 10, USER_DATA, BOOK_DATA);

		requestBookDAO.addRequestBook(requestedDetails);
		verify(requestBookDAO, times(1)).addRequestBook(requestedDetails);
	}
}
