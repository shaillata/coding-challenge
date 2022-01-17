package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserStoryNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2468622282776623840L;

	public UserStoryNotFoundException(String exception) {
		super(exception);
	}
}