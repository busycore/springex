package com.challenge.simpleApi.shared.errors.http;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Data
public class APIErrors {

    private List<String> Errors;

    public APIErrors(String message){
        this.Errors = Arrays.asList(message);
    }

    public APIErrors(List<String> errors) {
        this.Errors = errors;
    }
}
