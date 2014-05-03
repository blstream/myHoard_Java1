package com.blstream.myhoard.biz.validator;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.blstream.myhoard.biz.enums.RequestMethodEnum;
import com.blstream.myhoard.biz.model.CommentDTO;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.ValidatorException;

@Component
public class CommentValidator extends AbstractValidator {

    private final String KEY_NAME = "comment";
    private final String KEY_COLLECTION = "collection";

    public void validate(CommentDTO commentDTO, RequestMethodEnum requestMethod) throws MyHoardException {
        errorMessages = new HashMap<>();

        switch (requestMethod) {
            case POST:
                validateCreate(commentDTO);
                break;
            case PUT:
                validateUpdate(commentDTO);
                break;
            default:
                break;
        }

        checkError();
    }

    private void validateCreate(CommentDTO commentDTO) throws MyHoardException {
        if (commentDTO.getCollection() == null) {
            errorMessages.put(KEY_COLLECTION, MESSAGE_NOT_EMPTY);
        }

        if (commentDTO.getContent() != null) {
            commentDTO.setContent(commentDTO.getContent().trim());
        }

        if (commentDTO.getContent() == null) {
            errorMessages.put(KEY_NAME, MESSAGE_NOT_EMPTY);
        } else if (commentDTO.getContent().length() < 1 || commentDTO.getContent().length() > 160) {
            errorMessages.put(KEY_NAME, String.format(MESSAGE_LENGTH_MIN_MAX, 1, 160));
        }
    }

    private void validateUpdate(CommentDTO commentDTO) throws MyHoardException {
        if (commentDTO.getContent() != null) {
            commentDTO.setContent(commentDTO.getContent().trim());
            if (commentDTO.getContent().length() < 1 || commentDTO.getContent().length() > 160) {
                errorMessages.put(KEY_NAME, String.format(MESSAGE_LENGTH_MIN_MAX, 1, 160));
            }
        }
    }

    private void checkError() {
        if (!errorMessages.isEmpty()) {
            throw new ValidatorException(errorMessages);
        }
    }
}
