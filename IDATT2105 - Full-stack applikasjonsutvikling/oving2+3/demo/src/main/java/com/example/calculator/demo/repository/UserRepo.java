package com.example.calculator.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.calculator.demo.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUsername(String username);
} 
    
