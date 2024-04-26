////package com.erc.service;
////
////import com.erc.entity.User;
////import com.erc.model.AdminRequest;
////import com.erc.repository.UserRepository;
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.Test;
////import org.mockito.InjectMocks;
////import org.mockito.Mock;
////import org.mockito.MockitoAnnotations;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import static org.mockito.Mockito.*;
////
////class UserServiceImplTest {
////
////    @Mock
////    private UserRepository userRepository;
////    @Mock
////    private PasswordEncoder passwordEncoder;
////    @InjectMocks
////    private UserServiceImpl userService;
////    @BeforeEachpackage com.erc.service;
//
//import com.erc.entity.User;
//import com.erc.model.AdminRequest;
//import com.erc.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import static org.mockito.Mockito.*;
//
//class UserServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @InjectMocks
//    private UserServiceImpl userService;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void createAdmin() {
////
////        AdminRequest adminRequest = AdminRequest
////                .builder().build();
////
////        User expectedUser = User
////                .builder()
////                .build();
////
////        when(passwordEncoder.encode(adminRequest.getPassword())).thenReturn("encodedPassword");
////        userService.createAdmin(adminRequest);
////        verify(userRepository, times(1)).save(expectedUser);
//
//
//    }
//
//    @Test
//    void createCustomer() {
//    }
//
//    @Test
//    void signIn() {
//    }
//
//    @Test
//    void getAllUsers() {
//    }
//
//    @Test
//    void updateCustomer() {
//    }
//
//    @Test
//    void deleteCustomer() {
//    }
//
//    @Test
//    void loadUserByUsername() {
//    }
//}
////    void setUp() {
////        MockitoAnnotations.initMocks(this);
////    }
////
////    @Test
////    void createAdmin() {
//////
//////        AdminRequest adminRequest = AdminRequest
//////                .builder().build();
//////
//////        User expectedUser = User
//////                .builder()
//////                .build();
//////
//////        when(passwordEncoder.encode(adminRequest.getPassword())).thenReturn("encodedPassword");
//////        userService.createAdmin(adminRequest);
//////        verify(userRepository, times(1)).save(expectedUser);
////
////
////    }
////
////    @Test
////    void createCustomer() {
////    }
////
////    @Test
////    void signIn() {
////    }
////
////    @Test
////    void getAllUsers() {
////    }
////
////    @Test
////    void updateCustomer() {
////    }
////
////    @Test
////    void deleteCustomer() {
////    }
////
////    @Test
////    void loadUserByUsername() {
////    }
////}