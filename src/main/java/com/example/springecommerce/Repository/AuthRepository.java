package com.example.springecommerce.Repository;

import com.example.springecommerce.Model.Entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByEmail(String email);
    boolean existsByEmailIgnoreCase(String email);
}