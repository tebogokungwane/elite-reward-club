//package com.erc.service;//package com.erc.service;
//
////import com.erc.config.JwtService;
//import com.erc.entity.CustomerReward;
//import com.erc.entity.User;
//import com.erc.model.AuthenticationRequest;
//import com.erc.model.AuthenticationResponse;
//import com.erc.model.RegisterRequest;
//import com.erc.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@RequiredArgsConstructor
//@Service
//public class AuthenticationServiceImpl  {
//
//    private final UserRepository userRepository;
//    // private final JwtService jwtService;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationResponse register(RegisterRequest request) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//
//        CustomerReward customerReward = CustomerReward
//                .builder()
//                .customerRewardEmailAddress(request.getEmailAddress())
//                .rewardPointToIncrement(0)
//                .setRewardPoints(6)
//                .localDateTime(localDateTime)
//                .build();
//
//        User user = User.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .role(request.getRole())
//                .phoneNumber(request.getPhoneNumber())
//                .emailAddress(request.getEmailAddress())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .dateCreated(localDateTime)
//                .customerReward(customerReward)
//                // .company(request.get)
//                .build();
//
//        userRepository.save(user);
//       // String token = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(null)
//                .build();
//    }
//
////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
////        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
////        var user = userRepository.findByEmailAddress(request.getEmail()).orElseThrow();
////        String token = jwtService.generateToken(user);
////        return AuthenticationResponse.builder()
////                .token(token)
////                .build();
////    }
//
//}
