package com.erc.entity;//package com.erc.entity;
import jakarta.persistence.*;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "company_address")
public class CompanyAddress {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "company_network_id")
        private CompanyNetwork companyNetwork;

        private LocalDateTime localDateTimeCreated;

        // Constructors, getters, and setters
}
