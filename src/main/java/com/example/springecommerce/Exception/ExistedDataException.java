package com.example.springecommerce.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistedDataException extends RuntimeException {
    public ExistedDataException(){
        super("Data Already Exist");
    }
    public ExistedDataException(String message){
        super(message);
    }
}
