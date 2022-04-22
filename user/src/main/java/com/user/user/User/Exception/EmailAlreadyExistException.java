package com.user.user.User.Exception;

public class EmailAlreadyExistException extends RuntimeException {

  public EmailAlreadyExistException(String string) {
          this.message=string;
  }

  private static final long serialVersionUID = 1L;
  String message;

  @Override
  public String getMessage(){
    return message;
  }

}
