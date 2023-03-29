package com.example.springecommerce.Model.Request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String address;
    private String email;
    private String password;
}
