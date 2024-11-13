package com.showbook.Exception;

public class BookingNotFoundException extends RuntimeException {

	public BookingNotFoundException(String message) {
		super(message);
	}
}
