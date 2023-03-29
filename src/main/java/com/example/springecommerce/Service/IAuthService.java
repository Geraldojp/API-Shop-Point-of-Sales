package com.example.springecommerce.Service;

import com.example.springecommerce.Model.Request.LoginRequest;
import com.example.springecommerce.Model.Request.RegisterRequest;

public interface IAuthService {
    String login(LoginRequest loginRequest);
    String register(RegisterRequest registerRequest);
}
