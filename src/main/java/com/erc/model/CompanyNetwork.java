package com.erc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CompanyNetwork {
    private Long networkId;
    private String instagram;
    private String facebook;
    private String companyWebsite;
    private String whatsApp;
    private String tiktok;
    private String twitter;

}
