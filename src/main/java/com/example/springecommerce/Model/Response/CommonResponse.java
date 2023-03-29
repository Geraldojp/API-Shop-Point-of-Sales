package com.example.springecommerce.Model.Response;

import lombok.Getter;
import lombok.Setter;

public abstract class CommonResponse {
    @Getter@Setter
    private String status;
    @Getter@Setter
    private String message;
    @Getter@Setter
    private String code;
}
