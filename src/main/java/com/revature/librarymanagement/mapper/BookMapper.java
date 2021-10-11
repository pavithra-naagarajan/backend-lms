package com.revature.librarymanagement.mapper;

import com.revature.librarymanagement.dto.BookDto;
import com.revature.librarymanagement.model.Book;

public class BookMapper {

	// dto to entity mapping
	public static Book dtoToEntity(BookDto bookDto) {
		Book book = new Book();
		book.setBookId(bookDto.getBookId());
		book.setIsbn(bookDto.getIsbn());
		book.setBookName(bookDto.getBookName());
		book.setAuthorName(bookDto.getAuthorName());
		book.setPublisher(bookDto.getPublisher());
		book.setGenre(bookDto.getGenre());
		book.setVolume(bookDto.getVolume());
		book.setEdition(bookDto.getEdition());
		book.setCreatedOn(bookDto.getCreatedOn());
		book.setStatus(bookDto.getStatus());

		book.setUpdatedOn(bookDto.getUpdatedOn());
		book.setIssueBook(bookDto.getIssueBook());

		book.setRequestBook(bookDto.getRequestBook());

		return book;

	}
}
