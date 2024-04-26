package com.erc.service;

import com.erc.entity.CustomerReward;

import java.util.List;

public interface RewardClubService {
    //void createCustomer(User customer);
   // Page<User> getAllUsers(Pageable pageable);
    //List<Company> findAllCompanies();
    //List<User> getAllByRoleAndCompanyList_CompanyName(Role role, String companyName);
    CustomerReward getRewardCardByEmail(String emailAddress);
    List<CustomerReward> getAllRewardCustomers();

    //boolean deleteCustomer(long id);

    //User updateUser(long id, User customer );

   // Set<String> getAllCompanyName();

}
