package com.revature.librarymanagement.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "book")

public class Book implements Serializable {

	private static final long serialVersionUID = 36852902412299079L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@NotNull
	@Column(nullable = false, unique = true)
	private Long isbn;

	@NotNull
	@Column(nullable = false)
	private String bookName;

	@NotNull
	@Column(nullable = false)
	private String authorName;

	@NotNull
	@Column(nullable = false)
	private String publisher;
	
	@NotNull
	@Column(nullable = false)
	private String genre;

	@Column
	private int volume;

	@Column
	private int edition;

	@Column
	private String status;

	@Temporal(TemporalType.DATE)
	private Date createdOn;
	@Temporal(TemporalType.DATE)
	private Date updatedOn;

	@OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
	@JsonIgnore
	private IssueBook issueBook;

	@OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
	@JsonIgnore
	private RequestBook requestBook;

	public Book() {

	}

	public Book(Long bookId, Long isbn, String bookName, String authorName, String publisher, String genre, int volume,
			int edition, String status, Date createdOn, Date updatedOn, IssueBook issueBook, RequestBook requestBook) {
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

	public Book(Long bookId, Long isbn, String bookName, String authorName, String publisher, String genre, int volume,
			int edition, String status) {
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

	public RequestBook getRequestBook() {
		return requestBook;
	}

	public void setRequestBook(RequestBook requestBook) {
		this.requestBook = requestBook;
	}

	public IssueBook getIssueBook() {
		return issueBook;
	}

	public void setIssueBook(IssueBook issueBook) {
		this.issueBook = issueBook;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
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

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", isbn=" + isbn + ", bookName=" + bookName + ", authorName=" + authorName
				+ ", publisher=" + publisher + ", genre=" + genre + ", volume=" + volume + ", edition=" + edition
				+ ", status=" + status + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", issueBook="
				+ issueBook + ", requestBook=" + requestBook + "]";
	}

}
