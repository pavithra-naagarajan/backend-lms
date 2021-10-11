package com.revature.librarymanagement.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

import com.revature.librarymanagement.dao.RequestBookDAO;
import com.revature.librarymanagement.dto.RequestBookDto;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.exception.NullValueException;
import com.revature.librarymanagement.mapper.RequestBookMapper;
import com.revature.librarymanagement.model.RequestBook;
import com.revature.librarymanagement.service.RequestBookService;

@Service
public class RequestBookServiceImpl implements RequestBookService {
	private static final Logger logger = LogManager.getLogger(RequestBookServiceImpl.class);

	@Autowired
	private RequestBookDAO requestBookDAO;

	@Override
	public String addRequestBook(RequestBookDto requestBookDto) throws MethodArgumentNotValidException {
		logger.info("Entering add request book Function");

		RequestBook requestBook = RequestBookMapper.dtoToEntity(requestBookDto);
		if(requestBookDAO.getDetailsByRequestId(requestBook.getRequestId())!=null) {
			throw new DuplicateIdException(DUPLICATE_REQUESTEDEDBOOK);

		}
		try {
		return requestBookDAO.addRequestBook(requestBook);}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
			throw new MethodArgumentNotValidException(VALIDATION_FAIL);
		}
	}

	@Override
	public List<RequestBook> getAllRequestedBooks() {
		logger.info("Entering getAllRequestedBooks Function");

		List<RequestBook> requestedBooks= requestBookDAO.getAllRequestedBooks();
		if (CollectionUtils.isEmpty(requestedBooks))
			throw new NullValueException(NO_RECORDS);
		return requestedBooks;

	}

	@Override
	public String deleteRequestedBook(Long requestId) {
		logger.info("Entering delete Requested Book Function");
		if(requestBookDAO.getDetailsByRequestId(requestId)==null) {
			throw new IdNotFoundException(NOT_FOUND_TODELETE);
		}
		return requestBookDAO.deleteRequestedBook(requestId);
	}

	@Override
	public RequestBook getDetailsByRequestId(Long requestId) {
		logger.info("Entering get Details By RequestId Function");

		return requestBookDAO.getDetailsByRequestId(requestId);
	}

}
