package com.erc.repository;

import com.erc.entity.Company;
import com.erc.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.jdbc.core.JdbcOperationsExtensionsKt.query;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {



      EntityManager entityManager = null;

    Page<User> findAll(Pageable pageable);

    Optional<User> findByEmailAddress(String email);

    Page<User> findByCompany_CompanyName(String companyName, Pageable pageable);

    Optional<User> findById(byte points);

//    @Query("SELECT u FROM User u JOIN u.company c WHERE c.companyEmail = :companyEmail")
//    Page<User> findCustomersByCompanyEmail(
//            @Param("companyEmail") String companyEmail,
//            Pageable pageable);

    //@Query("SELECT u FROM User u WHERE u.company.companyEmail = :companyEmail")
    @Query(value = "SELECT u.* FROM users u JOIN company c ON u.company_id = c.id WHERE c.company_email = :companyEmail", nativeQuery = true)
    List<User> findByCompanyEmail(@Param("companyEmail") String companyEmail);

    }


