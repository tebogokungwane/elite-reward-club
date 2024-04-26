package com.erc.model;

import com.erc.enumerators.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyNameAndRoleRequestPayload {

    private Role role;
    private String companyName;

}
