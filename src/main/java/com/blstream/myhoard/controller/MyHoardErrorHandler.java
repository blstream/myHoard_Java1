package com.blstream.myhoard.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blstream.myhoard.exception.CollectionRestException;
import com.blstream.myhoard.exception.ErrorCode;
import com.blstream.myhoard.exception.NotFoundException;

@ControllerAdvice
public class MyHoardErrorHandler {
	
	private final static String ERROR_REASON_NOT_FOUND = "Resource Not Found";

	private final static Logger logger = Logger.getLogger(MyHoardErrorHandler.class.getName());

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
	public ErrorCode handleCollectionExceptionA(org.hibernate.PropertyValueException ex) {
		logger.log(Level.SEVERE, ex.toString());
		return new ErrorCode(111);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorCode handleException(Exception exception, HttpServletRequest request) {

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

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorCode handleException(NotFoundException e) {
		logger.log(Level.SEVERE, e.toString());
		
		return new ErrorCode(404, ERROR_REASON_NOT_FOUND);
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode handleException(HttpRequestMethodNotSupportedException e) {
		logger.log(Level.SEVERE, e.toString());
		
		return new ErrorCode(400, e.getMessage());
	}	

}
