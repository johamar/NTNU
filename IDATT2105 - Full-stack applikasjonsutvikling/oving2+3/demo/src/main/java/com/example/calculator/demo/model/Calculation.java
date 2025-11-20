package com.example.calculator.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "calculations")
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private double number1;
    private double number2;
    private String operation;
    private double result;

    public Calculation() {
    }

    public Calculation(String username, double number1, double number2, String operation, double result) {
        this.username = username;
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public double getNumber1() {
        return number1;
    }

    public double getNumber2() {
        return number2;
    }

    public String getOperation() {
       return operation;
    }

    public double getResult() {
        return result;
    }
}
