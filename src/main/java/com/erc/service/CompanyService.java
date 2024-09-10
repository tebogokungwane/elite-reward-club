package com.erc.service;

import com.erc.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAllCompanies();
   // List<User> getAllByRoleAndCompanyList_CompanyName(Role role, String companyName);

}
