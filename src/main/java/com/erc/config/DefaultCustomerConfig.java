package com.erc.config;

import com.erc.entity.Company;
import com.erc.entity.CustomerReward;
import com.erc.entity.User;
import com.erc.enumerators.Gender;
import com.erc.enumerators.Role;
import com.erc.repository.CompanyRepository;
import com.erc.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DefaultCustomerConfig {

    @Bean
    public CommandLineRunner createDefaultCustomer(UserRepository userRepository,
                                                   CompanyRepository companyRepository,
                                                   PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if the default customer already exists
            String defaultEmail = "management@example.com";
            if (userRepository.findByEmailAddress(defaultEmail).isEmpty()) {
                // Retrieve the existing company or create one if necessary
                String defaultCompanyName = "Default Company";
                Company company = companyRepository.findFirstByCompanyName(defaultCompanyName)
                        .orElseGet(() -> {
                            Company newCompany = new Company();
                            newCompany.setCompanyName(defaultCompanyName);
                            return companyRepository.save(newCompany);
                        });

                // Create a new CustomerReward instance
                CustomerReward customerReward = CustomerReward.builder()
                        .customerRewardEmailAddress(defaultEmail)
                        .rewardPointsTarget((byte) 5)
                        .rewardPointCurrent((byte) 0)
                        .formattedDate("2025-01-01")
                        .localDateTime(LocalDateTime.now())
                        .build();

                // Create a new User instance
                User customer = new User();
                customer.setCustomerReward(customerReward);
                customer.setCompany(company);
                customer.setFirstName("Management");
                customer.setLastName("User");
                customer.setEmailAddress(defaultEmail);
                customer.setPhoneNumber("1234567890");
                customer.setPassword(passwordEncoder.encode("password")); // Use a strong default password
                customer.setGender(Gender.MALE);
                customer.setActive(true);
                customer.setRole(Role.MANAGEMENT);
                customer.setRegisteredByUser("SYSTEM");
                customer.setLocalDateTime(LocalDateTime.now());

                // Save the default customer to the repository
                userRepository.save(customer);
            }
        };
    }
}
