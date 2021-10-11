package com.revature.librarymanagement.mapper;

import com.revature.librarymanagement.dto.RequestBookDto;
import com.revature.librarymanagement.model.RequestBook;

public class RequestBookMapper {

	// dto to entity mapping
	public static RequestBook dtoToEntity(RequestBookDto requestBookDto) {
		RequestBook requestBook = new RequestBook();

		requestBook.setRequestId(requestBookDto.getRequestId());
		requestBook.setNumberOfDays(requestBookDto.getNumberOfDays());
		requestBook.setBook(requestBookDto.getBook());
		requestBook.setUser(requestBookDto.getUser());

		return requestBook;

	}
}
