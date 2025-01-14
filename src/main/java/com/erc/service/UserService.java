package com.erc.service;

import com.erc.entity.User;
import com.erc.model.AdminRequest;
import com.erc.model.CustomerRequest;
import com.erc.model.LoginRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;


public interface UserService {

    //User createAdmin(CustomerRequest adminRequest);

    User createAdmin(AdminRequest adminRequest);


    User createCustomer(CustomerRequest userRequest) throws Exception;

    LoginRequest signIn(LoginRequest customerRequest);

   Page<User> getAllUsers(Pageable pageable);

    boolean deleteCustomer(long id);

    User updateCustomer(long id, CustomerRequest customer );

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User updateCustomerRewardPoints(long id, CustomerRequest customer);

    User updateMyCustomersTargetRewardPoint(long id, CustomerRequest customer);

    void sendPasswordResetToken(String email);

    @Query(value = "SELECT u.* FROM users u JOIN company c ON u.company_id = c.id WHERE c.company_email = :companyEmail", nativeQuery = true)
    List<User> findByCompanyEmail(@Param("companyEmail") String companyEmail);


    User updateCompany(long id, CustomerRequest customer);
}
