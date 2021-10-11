package com.revature.librarymanagement.exception;

public class IdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7246055979144846587L;

	public IdNotFoundException(String msg) {
		super(msg);
	}
}