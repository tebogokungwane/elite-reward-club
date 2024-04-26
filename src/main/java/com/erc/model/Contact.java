package com.erc.model;//package com.erc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {

    private String companyPhoneNumber;
    private String companyEmail;
    private String companyAddress;

}
