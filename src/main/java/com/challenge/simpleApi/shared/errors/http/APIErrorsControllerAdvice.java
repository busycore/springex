package com.challenge.simpleApi.shared.errors.http;

import com.challenge.simpleApi.shared.errors.exceptions.FileStorageException;
import com.challenge.simpleApi.shared.errors.exceptions.InvalidPassWordException;
import com.challenge.simpleApi.shared.errors.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class APIErrorsControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrors handleMethodNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());
        return new APIErrors(errors);
    }

    @ExceptionHandler(InvalidPassWordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrors handleInvalidPassWordException(InvalidPassWordException ex){
      return new APIErrors(ex.getMessage());
    }
    
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public APIErrors handleConstraintViolationException(ConstraintViolationException ex){
      
    List<String> errors = ex.getConstraintViolations().stream()
      .map(err -> err.getMessage())
      .collect(Collectors.toList());
    return new APIErrors(errors);
  }


  @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIErrors handleNotFoundException(NotFoundException exception) {
        return new APIErrors(exception.getMessage());
    }

  @ExceptionHandler(FileStorageException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public APIErrors handleFileStorageException(FileStorageException ex){
    return new APIErrors(ex.getMessage());
  }

}
