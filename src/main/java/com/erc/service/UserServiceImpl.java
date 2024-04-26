package com.erc.service;

import com.erc.entity.Company;
import com.erc.entity.CustomerReward;
import com.erc.entity.User;
import com.erc.model.AdminRequest;
import com.erc.model.CustomerRequest;
import com.erc.model.LoginRequest;
import com.erc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final LocalDateTime currentDateAndTime = LocalDateTime.now();
    private final UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void createAdmin(AdminRequest adminRequest) {

        log.info("UserServiceImpl::createAdmin()");

        Company company = Company
                .builder()
                .companyName(adminRequest.getCompany().getCompanyName())
                .companyService(adminRequest.getCompany().getCompanyService())
                .companyAddress(adminRequest.getCompany().getCompanyAddress())
                .companyEmail(adminRequest.getCompany().getCompanyEmail())
                .companyPhoneNumber(adminRequest.getCompany().getCompanyPhoneNumber())
                .companyCity(adminRequest.getCompany().getCompanyCity())
                .companyPostalCode(adminRequest.getCompany().getCompanyPostalCode())
                .localDateTime(currentDateAndTime)
                .build();

        CustomerReward customerReward = CustomerReward.builder()
                .customerRewardEmailAddress(adminRequest.getEmailAddress())
                .rewardPointToIncrement(0)
                .setRewardPoints(6)
                .localDateTime(LocalDateTime.from(currentDateAndTime))
                .build();

        User customerEntity = User
                .builder()
                .firstName(capitalizeFirstNameFirstLetter(adminRequest))
                .lastName(capitalizeLastNameFirstLetter(adminRequest))
                .emailAddress(adminRequest.getEmailAddress())
                .phoneNumber(adminRequest.getPhoneNumber())
                .dateCreated(currentDateAndTime)
                .gender(adminRequest.getGender())
                .isActive(true)
                .role(adminRequest.getRole())
                .password(passwordEncoder.encode(adminRequest.getPassword()))
                .company(company)
                .customerReward(customerReward)
                .build();

        userRepository.save(customerEntity);
    }

    @Override
    public void createCustomer(CustomerRequest customerRequest) throws Exception {
        log.info("UserServiceImpl::createCustomer()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        User loggedInAdmin = userRepository.findByEmailAddress(loggedInUsername)
                .orElseThrow(() -> new Exception("Admin not found"));

        Company company = loggedInAdmin.getCompany();
        CustomerReward customerReward = loggedInAdmin.getCustomerReward();

        User customerEntity = User
                .builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .emailAddress(customerRequest.getEmailAddress())
                .phoneNumber(customerRequest.getPhoneNumber())
                .dateCreated(currentDateAndTime)
                .gender(customerRequest.getGender())
                .isActive(true)
                .role(customerRequest.getRole())
                .password(customerRequest.getPassword())
                .company(company)
                .customerReward(customerReward)
                .build();

        userRepository.save(customerEntity);

    }

    @Override
    public LoginRequest signIn(LoginRequest customerRequest) {
        log.info("UserServiceImpl::signIn()");
        //LoginRequest response = new LoginRequest();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerRequest.getEmailAddress(), customerRequest.getPassword()));
            User user = userRepository.findByEmailAddress(customerRequest.getEmailAddress()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (passwordEncoder.matches(customerRequest.getPassword(), user.getPassword())) {
                String jwt = jwtUtils.generateToken(user);
                String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

                customerRequest.setStatusCode(200);
                customerRequest.setToken(jwt);
                customerRequest.setRefreshToken(refreshToken);
                customerRequest.setExpirationTime("24Hr");
                customerRequest.setMessage("Successfully logged in");

                log.info("Token gen : "+ jwt);


            } else {
                customerRequest.setStatusCode(401);
                customerRequest.setMessage("Unauthorized : Incorrect password");
            }
        }catch (Exception e) {
            log.error("Error during signIn", e);
            customerRequest.setStatusCode(401);
            customerRequest.setMessage("Unauthorized : " + e.getMessage());
        }

            return customerRequest;
        }


    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        if (pageable == null) {
            pageable = Pageable.unpaged();
        }
        return userRepository.findAll(pageable);
    }

    @Override
    public User updateCustomer(long id, CustomerRequest customer) {
        Optional<User> customerById = userRepository.findById(id);
        if (customerById.isEmpty()){
            throw new IllegalArgumentException("Customer not found");
        }
        User updateUserById = customerById.get();
        updateUserById.setRole(customer.getRole());
        updateUserById.setFirstName(customer.getFirstName());
        updateUserById.setLastName(customer.getLastName());
        updateUserById.setEmailAddress(customer.getEmailAddress());
        updateUserById.setPhoneNumber(customer.getPhoneNumber());
        updateUserById.setGender(customer.getGender());
        updateUserById.setPassword(passwordEncoder.encode(customer.getPassword()));
        userRepository.save(updateUserById);
        return updateUserById;
    }



    @Override
    public boolean deleteCustomer(long id) {
        User byId = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        userRepository.delete(byId);
        return true;
    }

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserServiceImpl::loadUserByUsername()");
        User user = userRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }

    private String capitalizeFirstNameFirstLetter(AdminRequest customer) {
        String firstName = customer.getFirstName();
        return firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    }

    private String capitalizeLastNameFirstLetter(AdminRequest customer) {
        String lastName = customer.getLastName();
        return lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
    }

//    private boolean isAuthenticationValid(String email, String password){
//
//        User user = userRepository.findByEmailAddress(customerRequest.getEmailAddress()).orElseThrow();
//
//        if
//
//    }
}
