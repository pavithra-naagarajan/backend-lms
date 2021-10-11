package com.revature.librarymanagement.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class ISBNGenerator {
	public static Long generateISBN() {

		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		return (long) number;
	}

}
