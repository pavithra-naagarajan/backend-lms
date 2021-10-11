package com.revature.librarymanagement.util;

public class LibraryManagementConstants {
	private LibraryManagementConstants() {
	}

	public static final String ERROR_IN_FETCH = "Error in fetching data from the DataBase!";
	public static final String ERROR_IN_DELETE = "Error in deleting from DataBase!";
	public static final String ERROR_IN_INSERT = "Error in adding to DataBase!";
	public static final String ERROR_IN_UPDATE = "Error in updating to DataBase!";

	public static final String NO_RECORDS = "No records found";
	public static final String GET_OPERATION = "Data retrieved successfully!";

	public static final String ID_NOT_FOUND = "Id not found!";
	public static final String NOT_FOUND_TODELETE = "Id not found to delete!";
	public static final String NOT_FOUND_TOUPDATE = "Id not found to update!";
	public static final String ADMIN_EXIST_ALREADY = "Admin account with this Id already exists!";
	
	public static final String INSERT_BOOK = "Book added successfully";
	public static final String UPDATE_BOOK = "Book updated successfully!";
	public static final String DELETE_BOOK = "Book deleted successfully with";
	public static final String UPDATE_BOOK_STATUS = "Book Status updated successfully!";
	
	
	public static final String  UPDATE_ADMIN="Admin details updated successfully!";
	public static final String  INSERT_ADMIN="Admin Account created with :";
	public static final String  DELETE_ADMIN="Admin Account deleted with :";

	
	public static final String  ISSUE_BOOK="Book is issued successfully with :";
	public static final String  UPDATE_ISSUE="Issued book details updated successfully!";
	public static final String  DELETE_ISSUE="Book issued details deleted with : " ;
	public static final String  UPDATE_DUEDATE="Due Date updated succesfully!";
	public static final String  UPDATE_FINE="Fine amount updated succesfully!";
	
	public static final String  INSERT_REQUEST="Book request is added successfully at ";
	public static final String  DELETE_REQUEST="Book requested details deleted at ";
	
	
	public static final String  DUPLICATE_USER="User account already exists with same details!";
	public static final String  USER_EXIST_ALREADY="User account with Id already exists!";
	
	public static final String  INSERT_USER="User Account created with : ";
	public static final String  UPDATE_USER="User details updated successfully!";
	public static final String  UPDATE_USER_STATUS="User status updated successfully!";
	public static final String  DELETE_USER="User Account deleted with : ";


	public static final String  DUPLICATE_BOOK="Book already exists with same ISBN!";
	public static final String  BOOK_EXIST_ALREADY="Book with Id already exists!";

	
	public static final String  DUPLICATE_ISSUEDBOOK="Issued book already exists with same Id!";

	
	
	public static final String  DUPLICATE_REQUESTEDEDBOOK="Requested book already exists with same Id!";

	public static final String  VALIDATION_FAIL="VALIDATION FAIL for given object!";

	public static final String  INPUT_MISMATCH="Given input is not matched with the method parameter!";
}
