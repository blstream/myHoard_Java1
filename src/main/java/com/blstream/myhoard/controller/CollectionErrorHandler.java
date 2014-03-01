package com.blstream.myhoard.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blstream.myhoard.exception.CollectionRestException;
import com.blstream.myhoard.exception.ErrorCode;

@ControllerAdvice
// @RequestMapping("/collections") this should be global?
// TODO add error reason
public class CollectionErrorHandler {

	private final static Logger logger = Logger
			.getLogger(CollectionErrorHandler.class.getName());

	@ExceptionHandler(CollectionRestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode handleCollectionException(CollectionRestException ex) {
		logger.log(Level.SEVERE, ex.toString());
		return new ErrorCode(ex.getCode());
	}

	// tymczasowe
	@ExceptionHandler(org.hibernate.PropertyValueException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode handleCollectionExceptionA(
			org.hibernate.PropertyValueException ex) {
		logger.log(Level.SEVERE, ex.toString());
		return new ErrorCode(111);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorCode handleException(Exception exception,
			HttpServletRequest request) {

		String method = request.getMethod();
		logger.log(Level.SEVERE, exception.toString());

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
