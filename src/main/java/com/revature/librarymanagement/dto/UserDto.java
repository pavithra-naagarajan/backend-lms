package com.revature.librarymanagement.dto;

import java.util.Date;

import java.util.Set;
import javax.validation.constraints.NotNull;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.RequestBook;
public class UserDto {

	private Long userId;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String password;
	@NotNull
	private String mailId;
	@NotNull
	private String mobileNumber;
	private int age;
	private String gender;
	private String address;
	@NotNull
	private String userRole;
	private Date createdOn;
	private Date updatedOn;
	private String status;
	private Set<IssueBook> issueBook;
	private Set<RequestBook> requestBook;

	public UserDto() {

	}

	public UserDto(Long userId, String firstName, String lastName, String password, String mailId, String mobileNumber,
			int age, String gender, String address, String userRole, Date createdOn, Date updatedOn, String status,
			Set<IssueBook> issueBook, Set<RequestBook> requestBook) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.mailId = mailId;
		this.mobileNumber = mobileNumber;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.userRole = userRole;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.status = status;
		this.issueBook = issueBook;
		this.requestBook = requestBook;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Set<IssueBook> getIssueBook() {
		return issueBook;
	}

	public void setIssueBook(Set<IssueBook> issueBook) {
		this.issueBook = issueBook;
	}

	public Set<RequestBook> getRequestBook() {
		return requestBook;
	}

	public void setRequestBook(Set<RequestBook> requestBook) {
		this.requestBook = requestBook;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", mailId=" + mailId + ", mobileNumber=" + mobileNumber + ", age=" + age + ", gender="
				+ gender + ", address=" + address + ", userRole=" + userRole + ", createdOn=" + createdOn
				+ ", updatedOn=" + updatedOn + ", status=" + status + ", issueBook=" + issueBook + ", requestBook="
				+ requestBook + "]";
	}

}
