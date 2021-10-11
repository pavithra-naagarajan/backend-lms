
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

import com.revature.librarymanagement.dao.UserDAO;
import com.revature.librarymanagement.exception.DatabaseException;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.util.AsynchronousMailSender;

@Repository
public class UserDAOImpl implements UserDAO {
	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_USER_BY_MOBILNUMBER = "select u from User u where u.mobileNumber=?1";
	private static final String GET_USER_BY_MAIL = "select u from User u where u.mailId=?1";
	private static final String GET_USER_BY_ROLE = "select u from User u where u.userRole LIKE?1";
	private static final String GET_ALL_USERS = "select u from User u";
	private static final String GET_USER_BY_NAME = "select u from User u where CONCAT(u.firstName,' ', u.lastName) LIKE ?1";
	private static final String USE_LOGIN = "select u from User u where u.mailId=?1 and u.password=?2";

	private static final String SEARCH_USERS = "select u from User u where u.firstName LIKE?1 OR u.lastName LIKE?2"
			+ "OR u.mailId LIKE?3 OR u.mobileNumber LIKE?4 OR u.userRole LIKE?5";

	@Transactional
	@Override
	public String addUser(User user) {
		logger.info("Entering add user Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			user.setCreatedOn(new Date());
			user.setStatus("Exist");
			session.save(user);
			AsynchronousMailSender.sendMail(user.getMailId(), "User Registration :",
					"Hi, " + user.getFirstName() + "\nYour account is created successfully in library management system!"
							+ "\nYou can login with your credentials and utilize all the functionalities given in user dashboard. Have a nice day!\n\nThank You.");

			return INSERT_USER + user.getUserId() + " at " + localTime;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_INSERT);
		}

	}

	@Transactional
	@Override
	public String updateUserStatus(Long userId, String status) {
		logger.info("Entering update user status Function");
		try {
			Session session = sessionFactory.getCurrentSession();
			User user = getUserById(userId);
			user.setStatus(status);
			session.update(user);

			return UPDATE_USER;

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_UPDATE);
		}

	}

	@Transactional
	@Override
	public String updateUser(User user) {
		logger.info("Entering update user Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			user.setUpdatedOn(new Date());
			session.merge(user);

			return UPDATE_USER_STATUS;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_UPDATE);
		}

	}

	@Transactional
	@Override
	public String deleteUserById(Long userId) {
		logger.info("Entering delete user Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			User user = getUserById(userId);

			session.delete(user);
			return DELETE_USER + userId + " at " + localTime;

		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_DELETE);
		}

	}

	@Override
	public User getUserById(Long userId) {
		logger.info("Entering get User By Id Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			return session.get(User.class, userId);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}

	}

	@Override
	public User getUserByMobileNumber(String mobileNumber) {
		logger.info("Entering get User By mobile number Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<User> resultList = session.createQuery(GET_USER_BY_MOBILNUMBER, User.class)
					.setParameter(1, mobileNumber).getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public User getUserByMailId(String mailId) {
		logger.info("Entering get User By mailId Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			List<User> resultList = session.createQuery(GET_USER_BY_MAIL, User.class).setParameter(1, mailId)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<User> getUserByRole(String userRole) {
		logger.info("Entering get User By user role Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			List<User> resultList = session.createQuery(GET_USER_BY_ROLE, User.class)
					.setParameter(1, "%" + userRole + "%").getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public boolean isUserExists(Long userId) {
		logger.info("Entering is User Exists Function");
try {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		return (user != null);}
		catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<User> getAllUsers() {
		logger.info("Entering get all users Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery(GET_ALL_USERS, User.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<User> getUserByFirstAndLastName(String name) {
		logger.info("Entering get User By FirstAndLastName Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<User> resultList = session.createQuery(GET_USER_BY_NAME, User.class).setParameter(1, "%" + name + "%")
					.getResultList();

			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public User userLogin(String mailId, String password) {
		logger.info("Entering user login Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			List<User> resultList = session.createQuery(USE_LOGIN, User.class).setParameter(1, mailId)
					.setParameter(2, password).getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Transactional
	@Override
	public String forgotPassword(String mailId, String password) {
		logger.info("Entering forgot password Function");

		try {

			Session session = sessionFactory.getCurrentSession();

			User user = getUserByMailId(mailId);
			user.setPassword(password);
			session.update(user);
			AsynchronousMailSender.sendMail(user.getMailId(), "New Password :",
					"Hi, " + user.getFirstName()
							+ "\nHere the new password generated for you.Use this password to login further!"
							+ "\n New Generated Password :" + user.getPassword() + "\n\nThank You.");
			return "Password generated and updated succesfully!";
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

	@Override
	public List<User> searchUsers(String value) {
		try {
			Session session = sessionFactory.getCurrentSession();

			List<User> resultList = session.createQuery(SEARCH_USERS, User.class).setParameter(1, "%" + value + "%")
					.setParameter(2, "%" + value + "%").setParameter(3, "%" + value + "%")
					.setParameter(4, "%" + value + "%").setParameter(5, "%" + value + "%").getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			throw new DatabaseException(ERROR_IN_FETCH);
		}
	}

}
