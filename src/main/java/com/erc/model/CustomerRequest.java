package com.erc.model;


import com.erc.enumerators.Gender;
import com.erc.enumerators.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class CustomerRequest {

    private Long customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private Gender gender;
    private boolean isActive;
    private LocalDateTime localDateTime;
    private Role role;
    private Company company;
    private CustomerReward customerReward;

//    private int statusCode;
//    private String error;
//    private String message;
//    private String token;
//    private String refreshToken;
//    private String expirationTime;

}

