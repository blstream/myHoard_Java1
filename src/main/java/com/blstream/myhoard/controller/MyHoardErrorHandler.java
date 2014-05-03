package com.blstream.myhoard.controller;

import static com.blstream.myhoard.constants.Constants.ERROR_CODE_AUTH_BAD_CREDENTIALS;
import static com.blstream.myhoard.constants.Constants.ERROR_CODE_AUTH_TOKEN_INVALID;
import static com.blstream.myhoard.constants.Constants.ERROR_CODE_AUTH_TOKEN_NOT_PROVIDED;
import static com.blstream.myhoard.constants.Constants.ERROR_CODE_AUTH_UNKNOW_ERROR;
import static com.blstream.myhoard.constants.Constants.ERROR_CODE_BAD_REQUEST;
import static com.blstream.myhoard.constants.Constants.ERROR_CODE_FORBIDDEN;
import static com.blstream.myhoard.constants.Constants.ERROR_CODE_NOT_FOUND;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.blstream.myhoard.constants.Constants;
import com.blstream.myhoard.exception.AuthorizationException;
import com.blstream.myhoard.exception.ErrorCode;
import com.blstream.myhoard.exception.ForbiddenException;
import com.blstream.myhoard.exception.MyHoardRestException;
import com.blstream.myhoard.exception.NotFoundException;
import com.blstream.myhoard.exception.ResourceAlreadyExistException;
import com.blstream.myhoard.exception.ValidatorException;

@ControllerAdvice
public class MyHoardErrorHandler {

    private final static String ERROR_MESSAGE_NOT_FOUND = "Resource Not Found";
    private final static String ERROR_MESSAGE_INCORRECT = "Incorrect Data";
    private final static String ERROR_MESSAGE_ALREADY_EXIST = "Resource Already Exist";

    private final static String ERROR_MESSAGE_AUTH_BAD_CREDENTIALS = "Bad credentials";
    private final static String ERROR_MESSAGE_AUTH_TOKEN_NOT_PROVIDED = "Token not provided";
    private final static String ERROR_MESSAGE_AUTH_TOKEN_INVALID_TOKEN = "Invalid token";
    private final static String ERROR_MESSAGE_AUTH_FORBIDDEN = "Forbidden";
    private final static String ERROR_MESSAGE_AUTH_UNKNOW_ERROR = "Unknown error";
    private final static String ERROR_MESSAGE_VALIDATION_ERROR = "Validation error";

    private static final Logger logger = Logger
            .getLogger(MyHoardErrorHandler.class.getCanonicalName());

    // TODO ???
    @ExceptionHandler(MyHoardRestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorCode handleCollectionException(MyHoardRestException ex) {
        logger.error("handleCollectionException", ex);

        return new ErrorCode(ex.getCode(), "Very bad request!");

    }

    // TODO errors map
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorCode handleException(NotFoundException e) {
        logger.error("handleException 404 - 202 " + e.getMessage(), e);

        if (e.getMessage() != null) {
            Map<String, String> errors = new HashMap<>();
            errors.put("resource", e.getMessage());
            return new ErrorCode(Constants.ERROR_CODE_NOT_FOUND, ERROR_MESSAGE_NOT_FOUND, errors);
        }
        return new ErrorCode(ERROR_CODE_NOT_FOUND, ERROR_MESSAGE_NOT_FOUND);
    }

    // TODO errors map
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorCode handleException(ResourceAlreadyExistException e) {
        logger.error("handleException 409 " + e.getMessage(), e);

        if (e.getMessage() != null) {
            return new ErrorCode(409, e.getMessage());
        }
        return new ErrorCode(409, ERROR_MESSAGE_ALREADY_EXIST);
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

        return new ErrorCode(400, String.format("%s", ERROR_MESSAGE_INCORRECT));
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorCode handleException(AuthorizationException e) {
        logger.error("handleException AuthorizationException 401 ", e);

        int errorCode = e.getErrorCode();

        switch (errorCode) {
            case ERROR_CODE_AUTH_BAD_CREDENTIALS:
                return new ErrorCode(errorCode, ERROR_MESSAGE_AUTH_BAD_CREDENTIALS);
            case ERROR_CODE_AUTH_TOKEN_NOT_PROVIDED:
                return new ErrorCode(errorCode, ERROR_MESSAGE_AUTH_TOKEN_NOT_PROVIDED);
            case ERROR_CODE_AUTH_TOKEN_INVALID:
                return new ErrorCode(errorCode, ERROR_MESSAGE_AUTH_TOKEN_INVALID_TOKEN);
            default:
                return new ErrorCode(ERROR_CODE_AUTH_UNKNOW_ERROR, ERROR_MESSAGE_AUTH_UNKNOW_ERROR);
        }
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorCode handleException(ForbiddenException e) {
        logger.error("handleException ForbiddedException 403 ", e);

        return new ErrorCode(ERROR_CODE_FORBIDDEN, ERROR_MESSAGE_AUTH_FORBIDDEN);
    }

    // TODO errors map
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

        return new ErrorCode(ERROR_CODE_BAD_REQUEST, sb.toString());
    }

    @ExceptionHandler(ValidatorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorCode validatorException(ValidatorException e) {
        logger.error("ValidatorException", e);

        return new ErrorCode(ERROR_CODE_BAD_REQUEST, ERROR_MESSAGE_VALIDATION_ERROR, e.getErrors());
    }
}
