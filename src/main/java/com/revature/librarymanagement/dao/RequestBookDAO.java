package com.revature.librarymanagement.dao;

import java.util.List;

import com.revature.librarymanagement.model.RequestBook;

public interface RequestBookDAO {
	public String addRequestBook(RequestBook requestBook);

	public List<RequestBook> getAllRequestedBooks();

	public String deleteRequestedBook(Long requestId);

	public RequestBook getDetailsByRequestId(Long requestId);

}
