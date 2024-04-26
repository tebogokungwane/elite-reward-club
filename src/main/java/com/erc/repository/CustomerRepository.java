//package com.erc.repository;//package com.erc.repository;
//
//import com.erc.entity.User;
//import com.erc.enumerators.Role;
//import org.springframework.data.domain.Page;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.data.domain.Pageable;
//import java.util.List;
//import java.util.Optional;
//
//
//@Repository
//public interface CustomerRepository extends JpaRepository<User,Long> {
//
//    Page<User> findAll(Pageable pageable);
//    List<User> getAllByRoleAndCompanyList_CompanyName(Role role, String companyName);
//    Optional<User> findByEmailAddress(String email);
//}
