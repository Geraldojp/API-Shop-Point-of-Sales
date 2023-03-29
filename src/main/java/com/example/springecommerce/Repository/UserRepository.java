package com.example.springecommerce.Repository;

import com.example.springecommerce.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}