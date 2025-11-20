package com.example.calculator.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.calculator.demo.model.Calculation;

public interface CalculationRepo extends JpaRepository<Calculation, Long> {
    List<Calculation> findByUsername(String username);
}
