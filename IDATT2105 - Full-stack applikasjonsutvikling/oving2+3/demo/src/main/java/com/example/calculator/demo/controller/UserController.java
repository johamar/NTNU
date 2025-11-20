// package com.example.calculator.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.calculator.demo.model.User;
// import com.example.calculator.demo.repository.UserRepo;

// @CrossOrigin(origins = "http://localhost:5173")
// @RestController
// @RequestMapping("/api/users")
// public class UserController {
//     // UserController class with methods for checkind if users exists in db and registering new users in db
//     // also methods for logging in and logging out users
//     // and methods for getting all users and deleting users
//     // and methods for getting all calculations and deleting calculations from calulatorController.java

//     @Autowired
//     private UserRepo userRepo;

//     // log in user with username and password

//     @PostMapping("/login")
//     public ResponseEntity<?> loginUser(@RequestBody User user) {
//         User dbUser = userRepo.findByUsername(user.getUsername());
//         if (dbUser == null) {
//             return ResponseEntity.status(400).body("Brukeren finnes ikke");
//         }
//         if (!dbUser.getPassword().equals(user.getPassword())) {
//             return ResponseEntity.status(400).body("Feil passord");
//         }
//         return ResponseEntity.ok("Logget inn");
//     }

//     // register new user in db
//     @PostMapping("/register")
//     public ResponseEntity<?> registerUser(@RequestBody User user) {
//         if (userRepo.findByUsername(user.getUsername()) != null) {
//             return ResponseEntity.status(400).body("Brukernavn er allerede tatt");
//         }
        
//         if (user.getPassword().length() < 8) {
//             return ResponseEntity.status(400).body("Passordet må være minst 8 tegn langt");
//         }

//         userRepo.save(user);
//         return ResponseEntity.ok("Bruker registrert");
//     }
// }