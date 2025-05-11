package gr.aueb.elearn.teacherapp.service.exceptions;

import gr.aueb.elearn.teacherapp.model.User;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
