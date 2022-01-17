package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class VotePointOutOfRangeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1159885919105973379L;

	public VotePointOutOfRangeException(String exception) {
		super(exception);
	}
}
