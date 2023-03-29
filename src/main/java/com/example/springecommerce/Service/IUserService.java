package com.example.springecommerce.Service;

import com.example.springecommerce.Model.Entity.User;
import com.example.springecommerce.Model.Request.UserRequest;

import java.util.Optional;

public interface IUserService {
    User save(User user);
    Optional<User> findById(Long id);
    Iterable<User> findAll();
    void delete(Long id);

}
