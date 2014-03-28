package com.blstream.myhoard.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 *   REST VALIDATION !!! 
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MySuperExtraException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	Map<String, String> errors;

	public MySuperExtraException() {
		super();
	}

	public MySuperExtraException(Map<String, String> errors) {
		super();
		this.errors = errors;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
