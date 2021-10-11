
package com.revature.librarymanagement.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity

@Table(name = "issue_book")

public class IssueBook implements Serializable {

	private static final long serialVersionUID = 8004148073440927934L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long issueId;

	@Temporal(TemporalType.DATE)
	private Date issueDate;
	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@Column
	private int fineAmount;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@NotNull
	@OneToOne
	@JoinColumn(nullable = false, unique = true)

	private Book book;

	public IssueBook() {

	}

	public IssueBook(Long issueId, Date issueDate, Date dueDate, int fineAmount, User user, Book book) {
		super();
		this.issueId = issueId;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.fineAmount = fineAmount;
		this.user = user;
		this.book = book;
	}

	public int getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
		return "IssueBook [issueId=" + issueId + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", fineAmount="
				+ fineAmount + ", user=" + user + ", book=" + book + "]";
	}

}
