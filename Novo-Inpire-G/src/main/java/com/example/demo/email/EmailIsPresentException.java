package com.example.demo.email;

public class EmailIsPresentException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailIsPresentException(String msg) {
		super(msg);
	}

}
