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
import com.revature.librarymanagement.dao.UserDAO;

import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class RevatureLMSUserTest {
	@Autowired
	private UserService userService;

	@MockBean
	private UserDAO userDAO;

	static final List<User> USER_TEST_DATA = Stream.of(
			new User(Long.valueOf(1), "DemoFirstName", "DemoLastName", "Demo123", "Demo@gmail.com", "1234567089",
					"Demo", 21, "Female", "pune"),
			new User(Long.valueOf(2), "DemoFirstName", "DemoLastName", "Demo1234", "Demo123@gmail.com", "9134567089",
					"Demo", 21, "Female", "pune"))
			.collect(Collectors.toList());

	static final List<User> USER_TEST_DATA1 = Stream.of(new User(Long.valueOf(1), "DemoFirstName", "DemoLastName",
			"Demo123", "Demo@gmail.com", "1234567089", "Student", 21, "Female", "pune")).collect(Collectors.toList());;

	@Test
	void getAllUsersTest() {

		when(userDAO.getAllUsers()).thenReturn(USER_TEST_DATA);

		assertEquals(2, userService.getAllUsers().size());
	}

	@Test
	void getUserByIdTest() {
		User user = new User(Long.valueOf(5), "pavithra", "Nagarajan", "pavi123", "pavi@gmail.com", "8525041821",
				"Student", 21, "Female", "chennai");
		when(userDAO.getUserById(Long.valueOf(5))).thenReturn(user);
		assertEquals(user, userDAO.getUserById(Long.valueOf(5)));
	}

	@Test
	void getUserByMailIdTest() {

		User user = new User(Long.valueOf(5), "pavithra", "Nagarajan", "pavi123", "pavi@gmail.com", "8525041821",
				"Student", 21, "Female", "chennai");
		userDAO.getUserByMailId("pavi@gmail.com");
		assertEquals("pavi@gmail.com", user.getMailId());
		verify(userDAO, times(1)).getUserByMailId(user.getMailId());

	}

	@Test
	void getUserByMobileNumberTest() {
		User user = new User(Long.valueOf(5), "pavithra", "Nagarajan", "pavi123", "pavi@gmail.com", "8525041821",
				"Student", 21, "Female", "chennai");
		userDAO.getUserByMobileNumber("8525041821");
		assertEquals("8525041821", user.getMobileNumber());
		verify(userDAO, times(1)).getUserByMobileNumber(user.getMobileNumber());

	}

	@Test
	void testGetCandidateByRole() {
		String userRole = "Student";
		when(userDAO.getUserByRole(userRole)).thenReturn(USER_TEST_DATA1);
		assertEquals(1, userService.getUserByRole(userRole).size());
	}

	@Test
	void deleteUserByIdTest() {

		User user = new User(Long.valueOf(5), "pavithra", "Nagarajan", "pavi123", "pavi@gmail.com", "8525041821",
				"Student", 21, "Female", "chennai");
		userDAO.deleteUserById(user.getUserId());
		verify(userDAO, times(1)).deleteUserById(user.getUserId());
	}

	@Test
	void addUserTest() {
		User user = new User(Long.valueOf(5), "pavithra", "Nagarajan", "pavi123", "pavi@gmail.com", "8525041821",
				"Student", 21, "Female", "chennai");
		userDAO.addUser(user);
		verify(userDAO, times(1)).addUser(user);
	}

	@Test
	void updateUserTest() {
		User user = new User(Long.valueOf(5), "pavithra", "Nagarajan", "pavi123", "pavi@gmail.com", "8525041821",
				"Student", 21, "Female", "chennai");

		userDAO.updateUser(user);
		verify(userDAO, times(1)).updateUser(user);
	}

}
