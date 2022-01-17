package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SessionNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1673354062086626151L;

	public SessionNotFoundException(String exception) {
		super(exception);
	}
}