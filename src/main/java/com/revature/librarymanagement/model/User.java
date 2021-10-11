package com.revature.librarymanagement.model;

import java.io.Serializable;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users_table")

public class User implements Serializable {

	private static final long serialVersionUID = 6032171646985801574L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotNull
	@Column(nullable = false)
	private String firstName;

	@NotNull
	@Column(nullable = false)
	private String lastName;

	@NotNull
	@Column(nullable = false)
	private String password;

	@NotNull
	@Column(nullable = false, unique = true)
	private String mailId;

	@NotNull
	@Column(nullable = false, unique = true)
	private String mobileNumber;

	@NotNull
	@Column(nullable = false)
	private String userRole;

	@Column
	private int age;

	@Column
	private String gender;

	@Column
	private String address;

	@Column
	private String status;
	@Temporal(TemporalType.DATE)
	private Date createdOn;
	@Temporal(TemporalType.DATE)
	private Date updatedOn;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<IssueBook> issueBook;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<RequestBook> requestBook;

	public User() {

	}

	public User(Long userId, String firstName, String lastName, String password, String mailId, String mobileNumber,
			String userRole, int age, String gender, String address, String status, Date createdOn, Date updatedOn,
			Set<IssueBook> issueBook, Set<RequestBook> requestBook) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.mailId = mailId;
		this.mobileNumber = mobileNumber;
		this.userRole = userRole;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.status = status;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.issueBook = issueBook;
		this.requestBook = requestBook;
	}

	public User(Long userId, String firstName, String lastName, String password, String mailId, String mobileNumber,
			String userRole, int age, String gender, String address) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.mailId = mailId;
		this.mobileNumber = mobileNumber;
		this.userRole = userRole;
		this.age = age;
		this.gender = gender;
		this.address = address;
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

	public Set<RequestBook> getRequestBook() {
		return requestBook;
	}

	public void setRequestBook(Set<RequestBook> requestBook) {
		this.requestBook = requestBook;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", mailId=" + mailId + ", mobileNumber=" + mobileNumber + ", userRole=" + userRole
				+ ", age=" + age + ", gender=" + gender + ", address=" + address + ", status=" + status + ", createdOn="
				+ createdOn + ", updatedOn=" + updatedOn + ", issueBook=" + issueBook + ", requestBook=" + requestBook
				+ "]";
	}

}
