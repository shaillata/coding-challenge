package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8726464276009311009L;

	public MemberNotFoundException(String exception) {
		super(exception);
	}
}