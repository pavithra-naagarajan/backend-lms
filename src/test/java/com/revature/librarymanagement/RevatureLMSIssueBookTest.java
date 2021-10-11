package com.revature.librarymanagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.librarymanagement.dao.IssueBookDAO;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.IssueBookService;

@RunWith(SpringRunner.class)
@SpringBootTest
class RevatureLMSIssueBookTest {

	@Autowired
	private IssueBookService issueBookService;

	@MockBean
	private IssueBookDAO issueBookDAO;

	static final User USER_DATA = new User(Long.valueOf(5), "pavithra", "Nagarajan", "pavi123", "pavi@gmail.com",
			"8525041821", "Student", 21, "Female", "chennai");

	static final Book BOOK_DATA = new Book(Long.valueOf(1), Long.valueOf(898778), "My world", "chetan bhagat", "Zara",
			"Comic", 1, 2, "Available");

	static final List<IssueBook> ISSUEBOOK_TEST_DATA = Stream
			.of(new IssueBook(Long.valueOf(1), new Date(), new Date(), 0, USER_DATA, BOOK_DATA))
			.collect(Collectors.toList());

	@Test
	void getAllIssuedDetailsTest() {

		when(issueBookDAO.getIssuedBooks()).thenReturn(ISSUEBOOK_TEST_DATA);

		assertEquals(1, issueBookService.getAllIssuedBooks().size());
	}

	@Test
	void getDetailsByIssueIdTest() {

		IssueBook issueDetails = new IssueBook(Long.valueOf(1), new Date(), new Date(), 0, USER_DATA, BOOK_DATA);
		issueBookDAO.getDetailsByIssueId(Long.valueOf(5));
		when(issueBookDAO.getDetailsByIssueId(Long.valueOf(1))).thenReturn(issueDetails);
		assertEquals(issueDetails, issueBookDAO.getDetailsByIssueId(Long.valueOf(1)));

	}

	@Test
	void getDetailsByBookIdTest() {

		IssueBook issueDetails = new IssueBook(Long.valueOf(1), new Date(), new Date(), 0, USER_DATA, BOOK_DATA);
		issueBookDAO.getDetailsByBookId(BOOK_DATA);
		when(issueBookDAO.getDetailsByBookId(BOOK_DATA)).thenReturn(issueDetails);
		assertEquals(issueDetails, issueBookDAO.getDetailsByBookId(BOOK_DATA));

	}

	@Test
	void getDetailsByUserIdTest() {
		List<IssueBook> isssuedDetails = Stream
				.of(new IssueBook(Long.valueOf(1), new Date(), new Date(), 0, USER_DATA, BOOK_DATA))
				.collect(Collectors.toList());
		issueBookDAO.getDetailsByUserId(USER_DATA);
		when(issueBookDAO.getDetailsByUserId(USER_DATA)).thenReturn(isssuedDetails);
		assertEquals(isssuedDetails, issueBookDAO.getDetailsByUserId(USER_DATA));

	}

	@Test
	void deleteIssuedBookTest() {

		IssueBook issueDetails = new IssueBook(Long.valueOf(1), new Date(), new Date(), 0, USER_DATA, BOOK_DATA);
		issueBookDAO.deleteIssuedBook(issueDetails.getIssueId());
		verify(issueBookDAO, times(1)).deleteIssuedBook(issueDetails.getIssueId());
	}

	@Test
	void addIssueBookTest() {
		IssueBook issueDetails = new IssueBook(Long.valueOf(1), new Date(), new Date(), 0, USER_DATA, BOOK_DATA);

		issueBookDAO.issueBook(issueDetails, 10);
		verify(issueBookDAO, times(1)).issueBook(issueDetails, 10);
	}

	@Test
	void updateIssueBookTest() {
		IssueBook issueDetails = new IssueBook(Long.valueOf(1), new Date(), new Date(), 0, USER_DATA, BOOK_DATA);

		issueBookDAO.updateIssuedBook(issueDetails);
		verify(issueBookDAO, times(1)).updateIssuedBook(issueDetails);
	}

}
