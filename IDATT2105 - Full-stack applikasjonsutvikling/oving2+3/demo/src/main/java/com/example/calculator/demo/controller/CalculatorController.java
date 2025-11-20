package com.example.calculator.demo.controller;

import com.example.calculator.demo.model.Calculation;
import com.example.calculator.demo.model.CalculationRequest;
import com.example.calculator.demo.model.CalculationResponse;
import com.example.calculator.demo.repository.CalculationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth/calculator")
public class CalculatorController {

    @Autowired
    private CalculationRepo calculationRepository;

    @PostMapping("/calculate")
    public CalculationResponse calculate(@RequestBody CalculationRequest request, Principal principal) {
        double result;
        switch (request.getOperation()) {
            case "+":
                result = request.getNumber1() + request.getNumber2();
                break;
            case "-":
                result = request.getNumber1() - request.getNumber2();
                break;
            case "*":
                result = request.getNumber1() * request.getNumber2();
                break;
            case "/":
                if (request.getNumber2() == 0) {
                    throw new ArithmeticException("Kan ikke dele på null!");
                }
                result = request.getNumber1() / request.getNumber2();
                break;
            default:
                throw new IllegalArgumentException("Ugyldig operator: " + request.getOperation());
        }
        if (principal == null) {
            throw new RuntimeException("Brukeren er ikke autentisert!");
        }
        System.out.println(Exception.class);
        // Lagre regnestykket i databasen med brukernavnet fra tokenet
        String username = principal.getName();
        Calculation calculation = new Calculation(username, request.getNumber1(), request.getNumber2(), request.getOperation(), result);
        calculationRepository.save(calculation);

        return new CalculationResponse(request.getNumber1(), request.getOperation(), request.getNumber2(), result);
    }

    @GetMapping("/history")
    public List<Calculation> getCalculationHistory(Principal principal) {
        String username = principal.getName();
        return calculationRepository.findByUsername(username);
    }
}