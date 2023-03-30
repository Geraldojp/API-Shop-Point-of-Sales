package com.example.springecommerce.Model.Response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class TransactionResponses<T> extends CommonResponse {
    @Getter
    @Setter
    private T data;
    @Getter @Setter
    private String total;
    public TransactionResponses(String message, T data, String total) {
        super.setCode("00");
        super.setMessage(message);
        super.setStatus(HttpStatus.OK.name());
        this.data = data;
        this.total = total;

    }
}
