package com.revature.librarymanagement.model;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity

@Table(name = "request_book")
public class RequestBook implements Serializable {

	private static final long serialVersionUID = 295019622363567395L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long requestId;

	@NotNull
	@Column(nullable = false)
	private int numberOfDays;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@NotNull
	@OneToOne
	@JoinColumn(nullable = false, unique = true)

	private Book book;

	public RequestBook() {

	}

	public RequestBook(Long requestId, int numberOfDays, User user, Book book) {
		super();
		this.requestId = requestId;
		this.numberOfDays = numberOfDays;
		this.user = user;
		this.book = book;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "RequestBook [requestId=" + requestId + ", numberOfDays=" + numberOfDays + ", user=" + user + ", book="
				+ book + "]";
	}

}
