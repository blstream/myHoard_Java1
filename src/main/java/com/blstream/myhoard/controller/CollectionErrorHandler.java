package com.blstream.myhoard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blstream.myhoard.exception.CollectionException;
import com.blstream.myhoard.exception.ErrorCode;

@ControllerAdvice
public class CollectionErrorHandler {

	@ExceptionHandler(CollectionException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode handleCollectionException(CollectionException ex) {
		return new ErrorCode(ex.getCode());
	}

	// tymczasowe
	@ExceptionHandler(org.hibernate.PropertyValueException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode handleCollectionExceptionA(
			org.hibernate.PropertyValueException ex) {
		return new ErrorCode(111);
	}

}
