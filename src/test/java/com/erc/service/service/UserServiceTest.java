//package com.erc.service.service;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import com.erc.entity.User;
//import com.erc.enumerators.Gender;
//import com.erc.enumerators.Role;
//import com.erc.model.Company;
//import com.erc.model.CustomerRequest;
//import com.erc.repository.CompanyRepository;
//import com.erc.repository.UserRepository;
//import com.erc.service.UserService;
//import com.erc.service.UserServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class UserServiceTest {
//
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateCustomer() throws Exception {
//        // Mock input data
//        //Company mockCompany = new Company();
//      String companyName = "Nine to Five";
//
//      //  mockCompany.setCompanyName("");
//
//        Company newCompany = Company.builder()
//                .companyName(companyName)
//                .isActive(true)
//                .addedBy("Pires")
//                .companyPhoneNumber("07452145555")
//                .companyEmail("psalm@gmail.com")
//                .companyAddress("Randburg")
//                .companyService("football club")
//                .build();
//
//
//        CustomerRequest customerRequest = new CustomerRequest();
//        customerRequest.setFirstName("John");
//        customerRequest.setLastName("Doe");
//        customerRequest.setEmailAddress("john.doe@example.com");
//        customerRequest.setPhoneNumber("1234567890");
//        customerRequest.setPassword("password123");
//        customerRequest.setGender(Gender.MALE);
//        customerRequest.setRole(Role.CUSTOMER);
//        customerRequest.setRegisteredByUser("admin@example.com");
//        customerRequest.setCompany(newCompany);
//
//        // Mock company repository return value
////        when(companyRepository.findFirstByCompanyName(anyString()))
////                .thenReturn((Optional<com.erc.entity.Company>) Optional.of(companyName));
//
//        // Mock password encoder return value
//        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
//
//        // Mock user repository save method
//        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
//
//        // Call the method to test
//        User savedCustomer = userService.createCustomer(customerRequest);
//
//        // Assert the result
//        assertNotNull(savedCustomer);
//        assertEquals("John", savedCustomer.getFirstName());
//        assertEquals("Doe", savedCustomer.getLastName());
//        assertEquals("john.doe@example.com", savedCustomer.getEmailAddress());
//        assertEquals("encodedPassword", savedCustomer.getPassword());
//        assertEquals(newCompany, savedCustomer.getCompany());
//        assertNotNull(savedCustomer.getCustomerReward());
//        assertEquals((byte) 5, savedCustomer.getCustomerReward().getRewardPointsTarget());
//        assertEquals((byte) 0, savedCustomer.getCustomerReward().getRewardPointCurrent());
//
//        // Verify repository interactions
//        verify(companyRepository, times(1)).findFirstByCompanyName(anyString());
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//    @Test
//    public void testCreateCustomerCompanyNotFound() {
//        // Mock input data
//        CustomerRequest customerRequest = new CustomerRequest();
//      //  customerRequest.setCompany(new Company("NonExistentCompany"));
//
//        // Mock company repository to return empty
//        when(companyRepository.findFirstByCompanyName(anyString()))
//                .thenReturn(Optional.empty());
//
//        // Assert that an exception is thrown
//        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
//            userService.createCustomer(customerRequest);
//        });
//
//        assertEquals("Company name not found", exception.getMessage());
//
//        // Verify that the userRepository.save method was never called
//        verify(userRepository, never()).save(any(User.class));
//    }
//}
//
