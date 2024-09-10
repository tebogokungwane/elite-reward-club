package com.erc.model;

import com.erc.entity.Company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CompanyMapper {

    public static Company mapToEntity(com.erc.model.Company companyModel) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(formatter);

        return Company.builder()
                .companyName(companyModel.getCompanyName())
                .companyService(companyModel.getCompanyService())
                .companyAddress(companyModel.getCompanyAddress())
                .companyEmail(companyModel.getCompanyEmail())
                .companyPhoneNumber(companyModel.getCompanyPhoneNumber())
                .addedBy(companyModel.getAddedBy())
                .formattedDate(formattedDate)
                .localDateTime(LocalDateTime.now())
                .rewardTargetPoints(companyModel.getRewardTargetPoints())
                .build();
    }
}

