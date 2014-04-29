package com.blstream.myhoard.biz.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.blstream.myhoard.exception.NotFoundException;

@Component
public class RequestValidator {

	private static final Logger logger = Logger
			.getLogger(RequestValidator.class.getCanonicalName());

	public void validId(String id) {

		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			throw new NotFoundException(String.format("Invalid ID: %s", id));
		}

	}

}
