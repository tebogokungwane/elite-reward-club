package com.erc.controller;

import com.erc.entity.User;
import com.erc.model.AdminRequest;
import com.erc.model.CustomerRequest;
import com.erc.model.ForgotPasswordRequest;
import com.erc.repository.UserRepository;
import com.erc.service.JWTUtils;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.erc.model.LoginRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/signUpAdmin")
     public ResponseEntity<User> createAdmin(@RequestBody AdminRequest adminRequest) {
        try {
            log.info("UserController::createAdmin() " + adminRequest);

            User admin = userService.createAdmin(adminRequest);
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            log.error("Error creating admin ", e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/signUpCustomer")
    public ResponseEntity<HttpStatus> createCustomer(@RequestBody CustomerRequest customerRequest) throws Exception {
        try {
            userService.createCustomer(customerRequest);
            return ResponseEntity.ok(HttpStatus.CREATED);
        }catch (Exception e) {
            log.error("Error creating customer ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginRequest> signIn(@RequestBody LoginRequest signInRequest){
        log.info("UserController::signIn()");

        return ResponseEntity.ok(userService.signIn(signInRequest));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9000") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("/company/customers")
    public ResponseEntity<List<User>> getCustomersByCompanyAndEmail(
            @RequestParam String companyEmail){
        List<User> customers = userService.findByCompanyEmail(companyEmail);

        return new ResponseEntity<>(customers, HttpStatus.OK);
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

    @PutMapping("/updateCustomerRewardPoints/{id}")
    public ResponseEntity<CustomerRequest> updateCustomerRewardPoints(@PathVariable long id, @RequestBody CustomerRequest customer){
        userService.updateCustomerRewardPoints(id,customer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/updateMyCustomersTargetRewardPoint/{id}")
    public ResponseEntity<CustomerRequest> updateMyCustomersTargetRewardPoint(@PathVariable long id, @RequestBody CustomerRequest customer){
        userService.updateMyCustomersTargetRewardPoint(id,customer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/api/v1/updateCompany/{id}")
    public ResponseEntity<CustomerRequest>updateCompany(@PathVariable long id, @RequestBody CustomerRequest customer){
        userService.updateCompany(id,customer);
        return ResponseEntity.ok(customer);
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request){
        try{

            userService.sendPasswordResetToken(request.getEmail());
            return ResponseEntity.ok("Password reset email send successfully.");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error sending reset password email.");
        }

    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<User> getProfile(@PathVariable String email) {

        log.info("UserController::profile()");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        System.out.println("Auhtenticated --->"+userEmail);

        Optional<User> byEmailAddress = userRepository.findByEmailAddress(email);

        if(byEmailAddress.isPresent()){
            User user = byEmailAddress.get();
            return ResponseEntity.ok(user);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refresh(@RequestHeader("Authorization") String token){

        String refreshedToken;
        HashMap<String, Object> claims = new HashMap<>();

        try{
            String actualToken = token.substring(7);
            String username = jwtUtils.extractUsername(actualToken);
            UserDetails userDetails = userService.loadUserByUsername(username);

            if(jwtUtils.isTokenValid(actualToken,userDetails)){
                refreshedToken = jwtUtils.generateRefreshToken(claims, userDetails);
                return ResponseEntity.ok(refreshedToken);
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is not valid");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to refresh token");
        }
    }
}
