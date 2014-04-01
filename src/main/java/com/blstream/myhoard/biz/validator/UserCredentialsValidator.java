package com.blstream.myhoard.biz.validator;

import com.blstream.myhoard.biz.model.UserCredentialsDTO;
import com.blstream.myhoard.constants.Constants;
import static com.blstream.myhoard.constants.Constants.GRNAT_TYPE_PASSWORD;
import static com.blstream.myhoard.constants.Constants.GRNAT_TYPE_REFRESH_TOKEN;
import com.blstream.myhoard.exception.AuthorizationException;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.ValidatorException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialsValidator extends AbstractValidator {

    private final String KEY_GRANT_TYPE = "grant_type";
    private final String KEY_REFRESH_TOKEN = "refresh_token";
    private final String KEY_EMAIL = "email";
    private final String KEY_PASS = "password";
    private final String MESSAGE_NOT_EMPTY = "may not be empty";

    private static final Logger logger = Logger.getLogger(UserCredentialsValidator.class.getCanonicalName());

    public void validate(UserCredentialsDTO credentials) throws MyHoardException {
        errorMessages = new HashMap<>();

        if (credentials.getGrantType() != null) {
            switch (credentials.getGrantType()) {
                case GRNAT_TYPE_PASSWORD:
                    validateCreateToken(credentials);
                    break;
                case GRNAT_TYPE_REFRESH_TOKEN:
                    validateRefreshToken(credentials);
                    break;
                default:
                    throw new AuthorizationException(Constants.ERROR_CODE_AUTH_BAD_CREDENTIALS);
            }
        } else {
            errorMessages.put(KEY_GRANT_TYPE, MESSAGE_NOT_EMPTY);
        }

        checkError();
    }

    private void validateCreateToken(UserCredentialsDTO credentials) throws MyHoardException {
        // email
        if (credentials.getEmail() == null) {
            errorMessages.put(KEY_EMAIL, MESSAGE_NOT_EMPTY);
        }

        // password
        if (credentials.getPassword() == null) {
            errorMessages.put(KEY_PASS, MESSAGE_NOT_EMPTY);
        }
    }

    private void validateRefreshToken(UserCredentialsDTO credentials) throws MyHoardException {
        // refresh token
        if (credentials.getRefreshToken() == null) {
            errorMessages.put(KEY_REFRESH_TOKEN, MESSAGE_NOT_EMPTY);
        }
    }

    private void checkError() {
        if (!errorMessages.isEmpty()) {
            throw new ValidatorException(errorMessages);
        }
    }

}
