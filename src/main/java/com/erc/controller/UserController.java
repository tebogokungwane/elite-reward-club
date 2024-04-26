package com.erc.controller;

import com.erc.entity.User;
import com.erc.model.AdminRequest;
import com.erc.model.CustomerRequest;
import com.erc.repository.UserRepository;
import com.erc.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.erc.model.LoginRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class UserController {

    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signUpAdmin")
     public ResponseEntity<HttpStatus> createAdmin(@RequestBody AdminRequest adminRequest) {
        log.info("UserController::createAdmin()");
            userService.createAdmin(adminRequest);
            return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/signUpCustomer")
    public ResponseEntity<HttpStatus> createCustomer(@RequestBody CustomerRequest customerRequest) throws Exception {
        log.info("UserController::createCustomer()");
        userService.createCustomer(customerRequest);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginRequest> signIn(@RequestBody LoginRequest signInRequest){
        log.info("UserController::signIn()");

        return ResponseEntity.ok(userService.signIn(signInRequest));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable long id) {
        boolean deleteCustomer = userService.deleteCustomer(id);
        Map<String, Boolean> response = new HashMap<>();
        if (deleteCustomer) {
            response.put("Deleted", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("Deleted", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<CustomerRequest> updateCustomer(@PathVariable long id, @RequestBody CustomerRequest customer){
        userService.updateCustomer(id, customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile() {

        log.info("UserController::profile()");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        System.out.println("Auhtenticated --->"+userEmail);

        Optional<User> byEmailAddress = userRepository.findByEmailAddress(userEmail);

        if(byEmailAddress.isPresent()){
            User user = byEmailAddress.get();
            return ResponseEntity.ok(user);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

}
