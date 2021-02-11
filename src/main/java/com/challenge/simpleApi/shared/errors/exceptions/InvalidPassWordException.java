package com.challenge.simpleApi.shared.errors.exceptions;

public class InvalidPassWordException extends RuntimeException{

  public InvalidPassWordException(String message) {
    super(message);
  }

  public InvalidPassWordException() {
    super("Password is incorrect");
  }
}
