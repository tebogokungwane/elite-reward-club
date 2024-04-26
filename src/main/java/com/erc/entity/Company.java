package com.erc.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "company")
public class Company {

    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "company_sequence")
    private long id;

    private String companyName;
    private String companyService;
    private boolean isActive;
    private String companyEmail;
    private String companyPhoneNumber;
    private String companyAddress;
    private String companyCity;
    private String companyPostalCode;
//    @OneToOne(
//            cascade = CascadeType.PERSIST,
//            fetch = FetchType.EAGER,
//            mappedBy = "companyNetwork"
//    )
    //private CompanyNetwork companyNetwork;
    private LocalDateTime localDateTime;
}
