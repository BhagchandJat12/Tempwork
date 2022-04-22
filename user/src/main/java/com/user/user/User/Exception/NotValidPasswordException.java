package com.user.user.User.Exception;

public class NotValidPasswordException extends RuntimeException {

    public NotValidPasswordException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 2L;
}
