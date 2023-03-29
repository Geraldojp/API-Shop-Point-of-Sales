package com.example.springecommerce.Model.Response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class SuccessResponse<T> extends CommonResponse {
    @Getter@Setter
    private T data;
    public SuccessResponse(String message, T data) {
        super.setCode("00");
        super.setMessage(message);
        super.setStatus(HttpStatus.OK.name());
        this.data = data;
    }
}
