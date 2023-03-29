package com.example.springecommerce.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmptyInputException extends RuntimeException{
    public EmptyInputException(){
        super("data can't be empty");
    }
    public EmptyInputException(String message){
        super(message);
    }
}
