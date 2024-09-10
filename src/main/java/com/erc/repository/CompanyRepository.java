package com.erc.repository;

import com.erc.entity.Company;
import com.erc.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    List<Company> findAll();
  // Optional<Company> findByCompanyName(String companyName);
    Optional<Company> findFirstByCompanyName(String companyName);


}
