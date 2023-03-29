package com.example.springecommerce.Service;

import com.example.springecommerce.Exception.NotFoundException;
import com.example.springecommerce.Model.Entity.User;
import com.example.springecommerce.Model.Request.UserRequest;
import com.example.springecommerce.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public User save(User user) {
        try {
            User newUser = modelMapper.map(user, User.class);
            return userRepository.save(newUser);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            Optional<User> find = userRepository.findById(id);
            if (find.isEmpty()) {
                throw new NotFoundException("Data not found");
            }
            return find;
        }catch (NotFoundException e) {
            throw e;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<User> findAll() {
        try {
            Iterable<User> find = userRepository.findAll();
            if (find == null) {
                throw new NotFoundException("Data not found");
            }
            return find;
        }catch (NotFoundException e) {
            throw e;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Optional<User> find = userRepository.findById(id);
            if (find.isEmpty()) {
                throw new NotFoundException("Data not found");
            }
            userRepository.deleteById(id);
        }catch (NotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
