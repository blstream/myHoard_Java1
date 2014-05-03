package com.blstream.myhoard.biz.validator;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.biz.enums.RequestMethodEnum;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.biz.service.UserService;
import com.blstream.myhoard.exception.ForbiddenException;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.ValidatorException;

@Component
public class UserValidator extends AbstractValidator {

    private final String KEY_EMAIL = "email";
    private final String KEY_USER = "user";
    private final String KEY_USERNAME = "username";
    private final String KEY_PASS = "password";

    private final String MESSAGE_EMAIL_EXIST = "user with email: %s already exist";
    private final String MESSAGE_EMAIL_INVALID = "invalid email-address: %s";
    private final static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    private static final Logger logger = Logger.getLogger(UserValidator.class.getCanonicalName());

    public void validate(UserDTO userDTO, RequestMethodEnum requestMethod) throws MyHoardException {
        errorMessages = new HashMap<>();

        switch (requestMethod) {
            case POST:
                validateCreate(userDTO);
                break;
            case PUT:
                validateUpdate(userDTO);
                break;
            default:
                break;
        }

        checkError();
    }

    private void validateCreate(UserDTO userDTO) throws MyHoardException {
        // email
        if (userDTO.getEmail() != null) {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(userDTO.getEmail());
            if (!matcher.matches()) {
                errorMessages.put(KEY_EMAIL, String.format(MESSAGE_EMAIL_INVALID, userDTO.getEmail()));
            }
        } else {
            errorMessages.put(KEY_EMAIL, MESSAGE_NOT_EMPTY);
        }

        // username
        if (userDTO.getUsername() != null && (userDTO.getUsername().length() < 2 || userDTO.getUsername().length() > 250)) {
            errorMessages.put(KEY_USERNAME, String.format(MESSAGE_LENGTH_MIN_MAX, 2, 250));
        }

        // password
        if (userDTO.getPassword() == null) {
            errorMessages.put(KEY_PASS, MESSAGE_NOT_EMPTY);
        } else if (userDTO.getPassword().length() < 4 || userDTO.getPassword().length() > 250) {
            errorMessages.put(KEY_PASS, String.format(MESSAGE_LENGTH_MIN_MAX, 4, 250));
        }

        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            errorMessages.put(KEY_USER, String.format(MESSAGE_EMAIL_EXIST, userDTO.getEmail()));
        }
    }

    private void validateUpdate(UserDTO userDTO) throws MyHoardException {
        UserDTO currentUser = securityService.getCurrentUser();
        if (!currentUser.getId().equals(userDTO.getId())) {
            throw new ForbiddenException();
        }

        // email
        if (userDTO.getEmail() != null) {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(userDTO.getEmail());
            if (!matcher.matches()) {
                errorMessages.put(KEY_EMAIL, String.format(MESSAGE_EMAIL_INVALID, userDTO.getEmail()));
            }
        }

        // username
        if (userDTO.getUsername() != null && (userDTO.getUsername().length() < 2 || userDTO.getUsername().length() > 250)) {
            errorMessages.put(KEY_USERNAME, String.format(MESSAGE_LENGTH_MIN_MAX, 2, 250));
        }

        // password
        if (userDTO.getPassword() != null && (userDTO.getPassword().length() < 4 || userDTO.getPassword().length() > 250)) {
            errorMessages.put(KEY_PASS, String.format(MESSAGE_LENGTH_MIN_MAX, 4, 250));
        }

        UserDTO tmpUserDTO = userService.getUserByEmail(userDTO.getEmail());
        if (tmpUserDTO != null && !currentUser.getId().equals(tmpUserDTO.getId())) {
            errorMessages.put(KEY_USER, String.format(MESSAGE_EMAIL_EXIST, userDTO.getEmail()));
        }
    }

    private void checkError() {
        if (!errorMessages.isEmpty()) {
            throw new ValidatorException(errorMessages);
        }
    }

}
