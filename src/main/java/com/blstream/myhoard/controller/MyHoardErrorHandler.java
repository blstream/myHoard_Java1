package com.blstream.myhoard.controller;

import com.blstream.myhoard.exception.CollectionRestException;
import com.blstream.myhoard.exception.ErrorCode;
import com.blstream.myhoard.exception.NotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyHoardErrorHandler {

        private final static String ERROR_REASON_NOT_FOUND = "Resource Not Found";

        private static final Logger logger = Logger.getLogger(MyHoardErrorHandler.class.getCanonicalName());

        @ExceptionHandler(CollectionRestException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public ErrorCode handleCollectionException(CollectionRestException ex) {
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
                logger.error("handleException 404", e);

                return new ErrorCode(404, ERROR_REASON_NOT_FOUND);
        }

        @ExceptionHandler
        @ResponseStatus(value = HttpStatus.BAD_REQUEST)
        @ResponseBody
        public ErrorCode handleException(HttpRequestMethodNotSupportedException e) {
                logger.error("handleException 400", e);

                return new ErrorCode(400, e.getMessage());
        }

}
