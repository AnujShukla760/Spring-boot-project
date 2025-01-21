package com.example.Employee.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExceptionClass extends RuntimeException{

    public ExceptionClass(String s) {
    }
}
