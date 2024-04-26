package com.erc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Company {


    private String companyName;
    private String companyService;
    private boolean isActive;
    private String companyEmail;
    private String companyPhoneNumber;
    private String companyAddress;
    private String companyCity;
    private String companyPostalCode;
   // private CompanyNetwork companyNetwork;
    private LocalDateTime localDateTimeCreated;

}