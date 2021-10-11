
package com.revature.librarymanagement.dao;

import java.util.List;

import com.revature.librarymanagement.model.User;

public interface UserDAO {

	public String addUser(User user);

	public String updateUser(User user);

	public String deleteUserById(Long userId);

	public User getUserById(Long userId);

	public List<User> getUserByFirstAndLastName(String name);

	public User getUserByMobileNumber(String mobileNumber);

	public User getUserByMailId(String mailId);

	public List<User> getUserByRole(String userRole);

	public boolean isUserExists(Long userId);

	public List<User> getAllUsers();

	public User userLogin(String mailId, String password);

	public String forgotPassword(String mailId, String password);

	public List<User> searchUsers(String value);

	public String updateUserStatus(Long userId, String status);

}
