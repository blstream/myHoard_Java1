package com.blstream.myhoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blstream.myhoard.exception.CollectionRestException;
import com.blstream.myhoard.exception.ErrorCode;

@ControllerAdvice
@RequestMapping("/collections")
public class CollectionErrorHandler {

	@ExceptionHandler(CollectionRestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode handleCollectionException(CollectionRestException ex) {
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

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorCode handleException(Exception exception,
			HttpServletRequest request) {

		String method = request.getMethod();

		switch (method) {
		case "POST":
			return new ErrorCode(400);
		case "PUT":
			return new ErrorCode(111);
		default:
			return new ErrorCode(0);

		}

	}

}
