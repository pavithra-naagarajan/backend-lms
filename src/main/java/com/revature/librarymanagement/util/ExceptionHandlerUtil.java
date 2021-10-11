package com.revature.librarymanagement.util;

import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.revature.librarymanagement.exception.DatabaseException;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.exception.NullValueException;
import com.revature.librarymanagement.response.HttpResponseStatus;
import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

@ControllerAdvice
public class ExceptionHandlerUtil {
	// exception for Duplicate Id insertion..
	@ExceptionHandler(DuplicateIdException.class)

	public ResponseEntity<HttpResponseStatus> duplicateIdFound(DuplicateIdException e) {
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	// exception for Id not Found ..
	@ExceptionHandler(IdNotFoundException.class)

	public ResponseEntity<HttpResponseStatus> idNotFound(IdNotFoundException e) {

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	// database Exception
	@ExceptionHandler(DatabaseException.class)

	public ResponseEntity<HttpResponseStatus> databaseException(DatabaseException e) {

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	// Null value exception
	@ExceptionHandler(NullValueException.class)

	public ResponseEntity<HttpResponseStatus> nullValueException(NullValueException e) {

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NO_CONTENT.value(), e.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	//validation handler
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<HttpResponseStatus> validationFailed(MethodArgumentNotValidException e) {
	       
		 return new ResponseEntity<>(new HttpResponseStatus (HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()),
				 HttpStatus.UNPROCESSABLE_ENTITY);
	        
	      
	 }

	
		
		//MethodArgumentTypeMismatchException
		@ExceptionHandler(MethodArgumentTypeMismatchException.class)
		public ResponseEntity<HttpResponseStatus> invalidInputArgumentsFound(MethodArgumentTypeMismatchException e) {
		
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(),INPUT_MISMATCH),
		HttpStatus.BAD_REQUEST);
		}
}
