package com.challenge.simpleApi.shared.errors.http;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Data
public class APIErrors {

  @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private List<String> Errors;

    public APIErrors(String message){
        
      this.Errors = Arrays.asList(message);
      this.timestamp = LocalDateTime.now();
    }

    public APIErrors(List<String> errors) {
        this.Errors = errors;
        this.timestamp = LocalDateTime.now();
    }
}
