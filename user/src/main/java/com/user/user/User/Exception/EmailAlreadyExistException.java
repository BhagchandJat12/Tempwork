package com.user.user.User.Exception;

public class EmailAlreadyExistException extends RuntimeException {

  public EmailAlreadyExistException(String message) {
    super(message);
  }

  private static final long serialVersionUID = 1L;

}
