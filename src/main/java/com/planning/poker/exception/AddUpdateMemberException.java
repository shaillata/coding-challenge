package com.planning.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AddUpdateMemberException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8974251613775167083L;

	public AddUpdateMemberException(String exception) {
		super(exception);
	}
}