//package com.erc.controller;
//
//import com.erc.config.JwtTokenProvider;
//import com.erc.entity.User;
//import com.erc.model.LoginRequest;
//import com.erc.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:3000")
//public class LoginController {
//
//    @Autowired
//    private final UserRepository userRepository;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        String email = loginRequest.getEmailAddress();
//        String password = loginRequest.getPassword();
//
//        if (email == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email address cannot be null");
//        }
//
//        Optional<User> optionalUser = userRepository.findByEmailAddressAndPassword(email, password);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            if (email.equals(user.getEmailAddress()) && password.equals(user.getPassword())) {
//                // Generate JWT token
//                String token = jwtTokenProvider.generateToken(email);
//                return ResponseEntity.ok(Map.of("token", token));
//            } else {
//                // User with the provided email and password does not match
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
//            }
//        } else {
//            // User with the provided email and password does not exist
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
//        }
//    }
//}
