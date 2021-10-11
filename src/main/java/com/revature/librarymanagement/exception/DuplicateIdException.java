package com.revature.librarymanagement.exception;

public class DuplicateIdException extends RuntimeException {

	private static final long serialVersionUID = -550105279094709335L;

	public DuplicateIdException(String msg) {
		super(msg);
	}
}