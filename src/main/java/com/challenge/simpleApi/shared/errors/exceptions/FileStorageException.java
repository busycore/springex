package com.challenge.simpleApi.shared.errors.exceptions;

public class FileStorageException extends RuntimeException {
  public FileStorageException(String message) {
    super(message);
  }
}
