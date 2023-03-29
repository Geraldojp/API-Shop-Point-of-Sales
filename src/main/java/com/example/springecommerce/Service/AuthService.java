package com.example.springecommerce.Service;

import com.example.springecommerce.Exception.ExistedDataException;
import com.example.springecommerce.Exception.NotFoundException;
import com.example.springecommerce.Model.Entity.Auth;
import com.example.springecommerce.Model.Entity.User;
import com.example.springecommerce.Model.Request.LoginRequest;
import com.example.springecommerce.Model.Request.RegisterRequest;
import com.example.springecommerce.Model.Request.UserRequest;
import com.example.springecommerce.Repository.AuthRepository;
import com.example.springecommerce.Utils.validation.JwtUtil;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AuthService implements IAuthService{
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Optional<Auth> auth = authRepository.findByEmail(loginRequest.getEmail());
            if (auth.isPresent() && auth.get().getPassword().equals(loginRequest.getPassword())) {
                return jwtUtil.generateToken(auth.get().getEmail());
            }else {
                throw new NotFoundException("User is not found");
            }
        }catch (NotFoundException e){
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override

    public String register(RegisterRequest registerRequest) {
        try {
            Auth auth = modelMapper.map(registerRequest, Auth.class);
            User newUser = modelMapper.map(registerRequest, User.class);
            if(authRepository.existsByEmailIgnoreCase(registerRequest.getEmail())){
                throw new ExistedDataException("Email already exist");
            }
            Auth authResult = authRepository.save(auth);
            newUser.setAuth(authResult);
            userService.save(newUser);
            return newUser.getName();
        }catch (ExistedDataException e){
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
