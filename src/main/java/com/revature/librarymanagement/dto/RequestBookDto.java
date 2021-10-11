package com.revature.librarymanagement.dto;

import javax.validation.constraints.NotNull;

import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.User;

public class RequestBookDto {
	private Long requestId;
	@NotNull
	private int numberOfDays;
	@NotNull
	private User user;
	@NotNull
	private Book book;

	public RequestBookDto() {

	}

	public RequestBookDto(Long requestId, int numberOfDays, User user, Book book) {
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
		return "RequestBookDto [requestId=" + requestId + ", numberOfDays=" + numberOfDays + ", user=" + user
				+ ", book=" + book + "]";
	}

}