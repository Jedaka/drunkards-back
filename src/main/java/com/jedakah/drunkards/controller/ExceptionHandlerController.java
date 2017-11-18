package com.jedakah.drunkards.controller;

import com.jedakah.drunkards.exceptions.ValidationException;
import com.jedakah.drunkards.to.ErrorTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler({ValidationException.class})
  public ResponseEntity<ErrorTO> handleValidationException(ValidationException e) {
    ErrorTO errorTO = new ErrorTO();
    errorTO.setError(e.getErrorMessage());

    return new ResponseEntity<>(errorTO, HttpStatus.UNPROCESSABLE_ENTITY);
  }

}
