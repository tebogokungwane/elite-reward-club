//package com.erc.controller;//package com.erc.controller;
//
//import com.erc.model.AuthenticationResponse;
//import com.erc.model.AuthenticationRequest;
//import com.erc.model.RegisterRequest;
//import com.erc.service.AuthenticationServiceImpl;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
//@Slf4j
//public class AuthenticationController {
//
//    private final AuthenticationServiceImpl authenticationService;
//
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
//        log.info("Inside /register ---> ()");
//
//        return ResponseEntity.ok(authenticationService.register(request));
//    }
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
//
//        log.info("Inside the auth method ---> ()");
//        return ResponseEntity.ok(authenticationService.authenticate(request));
//
//    }
//}
