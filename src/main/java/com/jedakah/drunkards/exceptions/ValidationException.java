package com.jedakah.drunkards.exceptions;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException {

  private String errorMessage;

  public ValidationException(String message) {

    this.errorMessage = message;
  }
}
