package com.erc.model;

import com.erc.enumerators.Gender;
import com.erc.enumerators.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AdminRequest {

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
    private String registeredByUser;
    private Company company;
    private CustomerReward customerReward;
}
