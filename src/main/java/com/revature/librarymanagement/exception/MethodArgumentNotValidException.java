package com.revature.librarymanagement.exception;

public class MethodArgumentNotValidException extends Exception{

	
	private static final long serialVersionUID = 1126171169846812535L;

	public MethodArgumentNotValidException(String msg) {
		super(msg);
	}
}
