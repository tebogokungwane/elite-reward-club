package com.erc.service;

import com.erc.entity.User;
import com.erc.model.CustomerRequest;
import com.erc.model.AdminRequest;
import com.erc.model.LoginRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

//import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService {

    void createAdmin(AdminRequest adminRequest);
    void createCustomer(CustomerRequest userRequest) throws Exception;
    LoginRequest signIn(LoginRequest customerRequest);
   Page<User> getAllUsers(Pageable pageable);
    boolean deleteCustomer(long id);
    User updateCustomer(long id, CustomerRequest customer );
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;




}
