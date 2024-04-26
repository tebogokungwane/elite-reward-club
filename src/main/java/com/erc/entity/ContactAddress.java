package com.erc.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class ContactAddress {

    private String companyEmail;
    private String companyPhoneNumber;
    private String companyAddress;
    private String companyCity;
    private String companyPostalCode;

}
