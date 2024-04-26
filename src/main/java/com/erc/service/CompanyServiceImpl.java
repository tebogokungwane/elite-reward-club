package com.erc.service;

import com.erc.entity.Company;
import com.erc.repository.CompanyRepository;
import com.erc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Company> findAllCompanies() {
        List<Company> CompanyList = companyRepository.findAll();
        if (CompanyList.isEmpty()){
            return Collections.emptyList();
        }
        return CompanyList;
    }

//    @Override
//    public List<User> getAllByRoleAndCompanyList_CompanyName(Role role, String companyName) {
//        List<User> allByRoleAndCompany = userRepository.getAllByRoleAndCompany(role, companyName);
//        if(allByRoleAndCompany.isEmpty()){
//            return Collections.emptyList();
//        }
//        return allByRoleAndCompany;
//    }

}
