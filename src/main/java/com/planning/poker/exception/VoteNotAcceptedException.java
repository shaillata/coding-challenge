package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class VoteNotAcceptedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7360948978039358771L;

	public VoteNotAcceptedException(String exception) {
		super(exception);
	}
}
