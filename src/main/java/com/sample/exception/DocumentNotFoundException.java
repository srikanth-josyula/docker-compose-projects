package com.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request data")
public class DocumentNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public DocumentNotFoundException(String msg) {
		super(msg);

	}
}
