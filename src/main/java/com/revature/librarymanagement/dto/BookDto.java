package com.revature.librarymanagement.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.RequestBook;

public class BookDto {
	
	
	private Long bookId;
	@NotNull
	private Long isbn;
	@NotNull
	private String bookName;
	@NotNull
	private String authorName;
	@NotNull
	private String publisher;
	@NotNull
	private String genre;
	private int volume;
	private int edition;
	private String status;
	private Date createdOn;
	private Date updatedOn;
	private IssueBook issueBook;
	private RequestBook requestBook;

	public BookDto() {

	}

	public BookDto(Long bookId, Long isbn, String bookName, String authorName, String publisher, String genre,
			int volume, int edition, String status, Date createdOn, Date updatedOn, IssueBook issueBook,
			RequestBook requestBook) {
		super();
		this.bookId = bookId;
		this.isbn = isbn;
		this.bookName = bookName;
		this.authorName = authorName;
		this.publisher = publisher;
		this.genre = genre;
		this.volume = volume;
		this.edition = edition;
		this.status = status;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.issueBook = issueBook;
		this.requestBook = requestBook;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
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

	public IssueBook getIssueBook() {
		return issueBook;
	}

	public void setIssueBook(IssueBook issueBook) {
		this.issueBook = issueBook;
	}

	public RequestBook getRequestBook() {
		return requestBook;
	}

	public void setRequestBook(RequestBook requestBook) {
		this.requestBook = requestBook;
	}

	@Override
	public String toString() {
		return "BookDto [bookId=" + bookId + ", isbn=" + isbn + ", bookName=" + bookName + ", authorName=" + authorName
				+ ", publisher=" + publisher + ", genre=" + genre + ", volume=" + volume + ", edition=" + edition
				+ ", status=" + status + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", issueBook="
				+ issueBook + ", requestBook=" + requestBook + "]";
	}

}
