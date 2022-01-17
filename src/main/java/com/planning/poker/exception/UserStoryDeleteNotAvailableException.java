package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UserStoryDeleteNotAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4118934189809422291L;

	public UserStoryDeleteNotAvailableException(String exception) {
		super(exception);
	}
}
