package com.example.calculator.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculationResponse {
  private double number1;
  private String operation;
  private double number2;
  private double result;
}

