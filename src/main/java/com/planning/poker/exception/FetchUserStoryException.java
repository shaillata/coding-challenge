package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FetchUserStoryException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6862372333699700609L;

	public FetchUserStoryException(String exception) {
		super(exception);
	}
}