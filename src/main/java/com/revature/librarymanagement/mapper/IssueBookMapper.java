package com.revature.librarymanagement.mapper;

import com.revature.librarymanagement.dto.IssueBookDto;
import com.revature.librarymanagement.model.IssueBook;

public class IssueBookMapper {
	// dto to entity mapping
	public static IssueBook dtoToEntity(IssueBookDto issueBookDto) {
		IssueBook issueBook = new IssueBook();
		issueBook.setIssueId(issueBookDto.getIssueId());
		issueBook.setIssueDate(issueBookDto.getIssueDate());
		issueBook.setDueDate(issueBookDto.getDueDate());

		issueBook.setFineAmount(issueBookDto.getFineAmount());

		issueBook.setBook(issueBookDto.getBook());
		issueBook.setUser(issueBookDto.getUser());

		return issueBook;

	}

}
