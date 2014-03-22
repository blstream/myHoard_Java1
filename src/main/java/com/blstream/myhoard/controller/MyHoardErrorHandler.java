package com.blstream.myhoard.controller;

import com.blstream.myhoard.exception.AuthorizationException;
import com.blstream.myhoard.exception.ErrorCode;
import com.blstream.myhoard.exception.MyHoardRestException;
import com.blstream.myhoard.exception.NotFoundException;
import com.blstream.myhoard.exception.ResourceAlreadyExistException;
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

        private final static String ERROR_MESSAGE_NOT_FOUND = "Resource Not Found";
        private final static String ERROR_MESSAGE_INCORRECT = "Incorrect Data";
        private final static String ERROR_MESSAGE_ALREADY_EXIST = "Resource Already Exist";

        private final static String ERROR_MESSAGE_AUTH_BAD_CREDENTIALS = "Bad credentials";
        private final static String ERROR_MESSAGE_AUTH_TOKEN_NOT_PROVIDED = "Token not provided";
        private final static String ERROR_MESSAGE_AUTH_TOKEN_INVALID_TOKEN = "Invalid token";
        private final static String ERROR_MESSAGE_AUTH_UNKNOW_ERROR = "Unknown error";

        private static final Logger logger = Logger.getLogger(MyHoardErrorHandler.class.getCanonicalName());

        @ExceptionHandler(MyHoardRestException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public ErrorCode handleCollectionException(MyHoardRestException ex) {
                logger.error("handleCollectionException", ex);
                return new ErrorCode(ex.getCode());
        }

        // tymczasowe
        @ExceptionHandler(org.hibernate.PropertyValueException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public ErrorCode handleCollectionExceptionA(org.hibernate.PropertyValueException ex) {
                logger.error("handleCollectionExceptionA", ex);
                return new ErrorCode(111);
        }

        @ExceptionHandler
        @ResponseBody
        @ResponseStatus(value = HttpStatus.BAD_REQUEST)
        public ErrorCode handleException(Exception exception, HttpServletRequest request) {
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
                return new ErrorCode(404, ERROR_MESSAGE_NOT_FOUND);
        }

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

        // TODO RT
        @ExceptionHandler
        @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
        @ResponseBody
        public ErrorCode handleException(AuthorizationException e) {
                logger.error("handleException AuthorizationException 401 ", e);

                switch (e.getErrorCode()) {
                        case 101:
                                return new ErrorCode(101, ERROR_MESSAGE_AUTH_BAD_CREDENTIALS);
                        case 102:
                                return new ErrorCode(102, ERROR_MESSAGE_AUTH_TOKEN_NOT_PROVIDED);
                        case 103:
                                return new ErrorCode(103, ERROR_MESSAGE_AUTH_TOKEN_INVALID_TOKEN);
                        default:
                                return new ErrorCode(401, ERROR_MESSAGE_AUTH_UNKNOW_ERROR);
                }
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
                        sb.append(String.format("%s: %s; ", fieldError.getField(), fieldError.getDefaultMessage()));
                }

                return new ErrorCode(400, sb.toString());
        }

}
