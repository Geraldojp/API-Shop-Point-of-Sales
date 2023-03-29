package com.example.springecommerce.Controller;

import com.example.springecommerce.Model.Entity.User;
import com.example.springecommerce.Model.Request.ProductRequest;
import com.example.springecommerce.Model.Request.UserRequest;
import com.example.springecommerce.Model.Response.SuccessResponse;
import com.example.springecommerce.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/registers")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping()
    public ResponseEntity save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessResponse<>("Saved", user));
    }
    @GetMapping
    public ResponseEntity findAll() {
        Iterable<User> find = userService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", find));
    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        User find = userService.findById(id).get();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", find));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Deleted", id + " has been deleted"));
    }

}
