package com.blstream.myhoard.controller;

import com.blstream.myhoard.exception.MyHoardRestException;
import com.blstream.myhoard.exception.ErrorCode;
import com.blstream.myhoard.exception.NotFoundException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyHoardErrorHandler {

	private final static String ERROR_REASON_NOT_FOUND = "Resource Not Found";
	private final static String ERROR_REASON_INCORRECT = "Incorrect Data";

	private static final Logger logger = Logger
			.getLogger(MyHoardErrorHandler.class.getCanonicalName());

	@ExceptionHandler(MyHoardRestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode handleCollectionException(MyHoardRestException ex) {
		logger.error("handleCollectionException", ex);

		if (ex.getCode() == 1000) {
			return new ErrorCode(400, "Very bad request parameters!");
		}
		return new ErrorCode(ex.getCode(), "Very bad request!");
	}

	// tymczasowe
	@ExceptionHandler(org.hibernate.PropertyValueException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode handleCollectionExceptionA(
			org.hibernate.PropertyValueException ex) {
		logger.error("handleCollectionExceptionA", ex);
		return new ErrorCode(111);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorCode handleException(Exception exception,
			HttpServletRequest request) {
		String method = request.getMethod();
		logger.error("handleException", exception);

		switch (method) {
		case "POST":
			logger.info("ErrorCode 400");
			return new ErrorCode(400);
		case "PUT":
			logger.info("ErrorCode 111");
			return new ErrorCode(111);
		default:
			logger.info("ErrorCode default");
			return new ErrorCode(0);
		}
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorCode handleException(NotFoundException e) {
		logger.error("handleException 404 " + e.getMessage(), e);

		if (e.getMessage() != null) {
			return new ErrorCode(404, e.getMessage());
		}
		return new ErrorCode(404, ERROR_REASON_NOT_FOUND);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode handleException(HttpRequestMethodNotSupportedException e) {
		logger.error("handleException 400", e);

		return new ErrorCode(400, e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode handleException(HttpMessageNotReadableException e) {
		logger.error("handleException 400", e);

		return new ErrorCode(400, String.format("%s", ERROR_REASON_INCORRECT));
	}

	// DTO objects validation
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorCode processValidationError(MethodArgumentNotValidException e) {
		logger.error("Validation Error", e);

		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		StringBuilder sb = new StringBuilder();
		for (FieldError fieldError : fieldErrors) {
			sb.append(String.format("%s: %s; ", fieldError.getField(),
					fieldError.getDefaultMessage()));
		}

		return new ErrorCode(400, sb.toString());
	}

}
