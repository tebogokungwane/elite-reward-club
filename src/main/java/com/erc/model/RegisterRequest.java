package com.erc.model;

import com.erc.enumerators.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Long customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private LocalDateTime localDateTime;
    private Role role;
    private List<Company> CompanyList = new ArrayList<>();
    private CustomerReward customerReward;
}
