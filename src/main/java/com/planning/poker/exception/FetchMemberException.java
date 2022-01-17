package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FetchMemberException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4537653348919600735L;

	public FetchMemberException(String exception) {
		super(exception);
	}
}