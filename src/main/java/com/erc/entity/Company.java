package com.erc.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( unique = true, nullable = false)
    private String companyName;
    private String companyService;
    private boolean isActive;
    private String companyEmail;
    private String companyPhoneNumber;
    private String companyAddress;
    private String companyCity;
    private String companyPostalCode;
    private String addedBy;
    @Column(name = "reward_target_points")
    private Integer rewardTargetPoints;
    @Column(name = "created_at")
    private LocalDateTime localDateTime;

    @Column(name = "formatted_created_at")
    private String formattedDate;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        this.formattedDate = formatDateTime(localDateTime);
    }

    private String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        return localDateTime.format(formatter);
    }

}
