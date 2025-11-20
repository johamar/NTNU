package com.example.calculator.demo.model;

import lombok.Data;

@Data
public class CalculationRequest {
  private double number1;
  private double number2;
  private String operation;
}

