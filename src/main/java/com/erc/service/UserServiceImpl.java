package com.erc.service;

import com.erc.entity.Company;
import com.erc.entity.CustomerReward;
import com.erc.entity.User;
import com.erc.enumerators.Role;
import com.erc.exception.ErrorCode;
import com.erc.model.AdminRequest;
import com.erc.model.CompanyMapper;
import com.erc.model.CustomerRequest;
import com.erc.model.LoginRequest;
import com.erc.repository.CompanyRepository;
import com.erc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.JavaMailSender;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CompanyRepository companyRepository;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
    LocalDate currentDate = LocalDate.now();
    String formattedDate = currentDate.format(formatter);

    public UserServiceImpl(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }


    CustomerRequest request = new CustomerRequest();

    @Override
    @Transactional

    public User createAdmin(AdminRequest adminRequest) {

        String defaultPassword = "password";
        String encodedPassword = passwordEncoder.encode(defaultPassword);

        Company company = CompanyMapper.mapToEntity(adminRequest.getCompany());

        company.setRewardTargetPoints(5);
        company = companyRepository.save(company);
        User user = new User();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String authenticationName = authentication.getName();

        user.setFirstName(adminRequest.getFirstName());
        user.setLastName(adminRequest.getLastName());
        user.setEmailAddress(adminRequest.getEmailAddress());
        user.setPhoneNumber(adminRequest.getPhoneNumber());

         userRepository.findByEmailAddress(
                adminRequest.getEmailAddress())
                .ifPresent(existingUser -> {
                    throw new IllegalArgumentException(ErrorCode.USER_REGISTRATION_ERROR.getMessage(adminRequest.getEmailAddress()));
                });

        if (adminRequest.getPassword() == null || adminRequest.getPassword().isEmpty()) {
            user.setPassword(encodedPassword);
        } else {
            user.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
        }

        user.setGender(adminRequest.getGender());
        user.setActive(adminRequest.isActive());
        user.setRegisteredByUser(authenticationName);
        user.setLocalDateTime(LocalDateTime.now());
        user.setRole(Role.ADMIN);
        user.setCompany(company);

        CustomerReward customerReward = new CustomerReward();
        customerReward.setRewardPointsTarget(0);
        customerReward.setCustomerRewardEmailAddress(adminRequest.getEmailAddress());

        user.setCustomerReward(customerReward);

        log.info("User : "+ user);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User createCustomer(CustomerRequest customerRequest) {

        User customer = new User();

        String companyName = customerRequest.getCompany().getCompanyName();
        Company existingCompany = companyRepository.findFirstByCompanyName(companyName)
                .orElseThrow(() -> new IllegalStateException("Company name not found"));

        userRepository.findByEmailAddress(
                        customerRequest.getEmailAddress())
                .ifPresent(existingUser -> {
                    throw new IllegalArgumentException("User already exist " + customerRequest.getEmailAddress());
                });

        CustomerReward customerReward = CustomerReward.
                builder()
                .customerRewardEmailAddress(customerRequest.getEmailAddress())
                .rewardPointsTarget((byte) 5)
               .rewardPointCurrent((byte)0)
                .formattedDate(formattedDate)
                .localDateTime(LocalDateTime.now())
                .build();

        customer.setCustomerReward(customerReward);
        customer.setCompany(existingCompany);
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmailAddress(customerRequest.getEmailAddress());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        customer.setGender(customerRequest.getGender());
        customer.setActive(true);
        customer.setRole(customerRequest.getRole());
        customer.setRegisteredByUser(customerRequest.getRegisteredByUser());
        customer.setLocalDateTime(LocalDateTime.now());

        log.info("Customer request to be save {}"+customer);

        return userRepository.save(customer);
    }

    @Override
    public LoginRequest signIn(LoginRequest customerRequest) {
        log.info("UserServiceImpl::signIn()");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerRequest.getEmailAddress(), customerRequest.getPassword()));
            User user = userRepository.findByEmailAddress(customerRequest.getEmailAddress()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String companyName = user.getCompany().getCompanyName();

            String jwt = jwtUtils.generateToken(user, companyName);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            customerRequest.setStatusCode(200);
            customerRequest.setToken(jwt);
            customerRequest.setRefreshToken(refreshToken);
            customerRequest.setExpirationTime("24Hr");
            customerRequest.setMessage("Successfully logged in");

            log.info("Token generated : " + jwt);

        } catch (UsernameNotFoundException e) {
            log.error("User not found", e);
            customerRequest.setStatusCode(404);
            customerRequest.setMessage("User not found");

        } catch (BadCredentialsException e) {
            log.error("Incorrect password", e);
            customerRequest.setStatusCode(401);
            customerRequest.setMessage("Incorrect password");

        } catch (Exception e) {
            log.error("Error during signIn", e);
            customerRequest.setStatusCode(500);
            customerRequest.setMessage("Internal server error : " + e.getMessage());
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
        if (customerById.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }
        User updateUserById = customerById.get();
//        updateUserById.setRole(customer.getRole());
        updateUserById.setActive(customer.isActive());
        updateUserById.setFirstName(customer.getFirstName());
        updateUserById.setLastName(customer.getLastName());
        updateUserById.setEmailAddress(customer.getEmailAddress());
        updateUserById.setPhoneNumber(customer.getPhoneNumber());
        updateUserById.setGender(customer.getGender());
//        updateUserById.setPassword(passwordEncoder.encode(customer.getPassword()));
        userRepository.save(updateUserById);
        return updateUserById;
    }

    @Override
    public User updateCustomerRewardPoints(long id, CustomerRequest customerRequest) {
                Optional<User> customersTargetRewardPoint = userRepository.findById(id);
        if (customersTargetRewardPoint.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }
        User user = customersTargetRewardPoint.get();
        CustomerReward customerReward = user.getCustomerReward();
        customerReward.setRewardPointsTarget(customerRequest.getCustomerReward().getRewardPointsTarget());
        LocalDateTime now = LocalDateTime.now();
        customerRequest.setLocalDateTime(now);

        return userRepository.save(user);
    }


    @Override
    public User updateMyCustomersTargetRewardPoint(long id, CustomerRequest customer) {

        // Find the user by ID
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }

        User user = userOptional.get();
        CustomerReward customerReward = user.getCustomerReward();

        // Check if the last update was on the same day
        LocalDateTime now = LocalDateTime.now();
        LocalDate lastUpdatedDate = customerReward.getLastUpdatedDate() != null
                ? customerReward.getLastUpdatedDate().toLocalDate()
                : now.toLocalDate().minusDays(1);

        // Allow updating if the current date is after the last updated date
        if (now.toLocalDate().isAfter(lastUpdatedDate)) {
            long currentPoints = customerReward.getRewardPointCurrent();
            long newPoints = Math.min(5, currentPoints + 1);

            customerReward.setRewardPointCurrent(newPoints);
            customerReward.setLocalDateTime(now);  // Update the last updated date

            userRepository.save(user);

            return user;  // Return the updated user
        } else {
            throw new IllegalStateException("You can only update your points once a day");
        }
    }


    @Override
    public User updateCompany(long id, CustomerRequest customer) {

        Optional<User> byId = userRepository.findById(id);

        if (byId.isEmpty()){
            throw new IllegalArgumentException("Company not found");
        }

        User user = byId.get();
        Company company = user.getCompany();

        company.setCompanyName(customer.getCompany().getCompanyName());
        company.setCompanyService(customer.getCompany().getCompanyService());
        company.setCompanyEmail(customer.getCompany().getCompanyEmail());
        company.setCompanyPhoneNumber(customer.getCompany().getCompanyPhoneNumber());
        company.setCompanyAddress(customer.getCompany().getCompanyAddress());
        company.setRewardTargetPoints(customer.getCompany().getRewardTargetPoints());

        userRepository.save(user);

        return user;
    }

    public void sendPasswordResetToken(String emailAddress) {
        Optional<User> userOptional = userRepository.findByEmailAddress(emailAddress);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No user found with that email address.");
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString();

        user.setResetToken(token);
        userRepository.save(user);

        sendResetEmail(user.getEmailAddress(), token);
    }

    private void sendResetEmail(String email, String token) {
        try {
        String resetUrl = "http://localhost:3000/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + resetUrl);

        log.info("From :"+ message.getFrom());
        log.info("Email :" + email);
        log.info("Subject "+ message.getSubject());
        log.info("Text "+ message.getText());

            mailSender.send(message);
        } catch (MailException ex) {
            log.error("Failed to send email", ex);
        }

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

    @Override
    public List<User> findByCompanyEmail(String companyEmail) {
        List<User> byCompanyEmail = userRepository.findByCompanyEmail(companyEmail);
        System.out.println("Query Results :"+ byCompanyEmail);
        return byCompanyEmail ;
    }



    private String capitalizeFirstNameFirstLetter() {
        String customer = request.getFirstName();
        return customer.substring(0, 1).toUpperCase() + customer.substring(1);
    }

    private String capitalizeLastNameFirstLetter() {

        String customer = request.getLastName();
        return customer.substring(0, 1).toUpperCase() + customer.substring(1);
    }
}
