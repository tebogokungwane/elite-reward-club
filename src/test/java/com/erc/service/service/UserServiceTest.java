package com.erc.service.service;

import com.erc.entity.Company;
import com.erc.entity.User;
import com.erc.enumerators.Gender;
import com.erc.enumerators.Role;
import com.erc.repository.CompanyRepository;
import com.erc.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;

   private Company company;
   private User user;

    @BeforeEach
    void setUp() {

        Company company = Company
                .builder()
                .companyName("companyName")
                .isActive(true)
                .addedBy("Pires")
                .companyPhoneNumber("07452145555")
                .companyEmail("company@gmail.com")
                .companyAddress("Zon6 baragwanath")
                .companyService("Soul wining")
                .build();

        companyRepository.save(company);

        User user = User.builder()
                .firstName("Tebogo")
                .lastName("Scott")
                .password("password")
                .role(Role.ADMIN)
                .gender(Gender.MALE)
                .emailAddress("tjkungwane@gmail.com")
                .phoneNumber("0725016666")
                .company(company)
                .build();
    }

    @Test
    void testIfUserIsSaved(){
        User saveUser = userRepository.save(user);
        assertThat(saveUser).isNotNull();
        assertThat(saveUser.getId()).isNotNull();
        assertThat(saveUser.getFirstName()).isEqualTo("Tebogo");
        assertThat(saveUser.getCompany().getCompanyName()).isEqualTo("companyName");
    }

//    @Test
//    void testFindCompany_CompanyName(){
//        Pageable pageable = Pageable.ofSize(10);
//        Page<User> byCompanyCompanyName = userRepository.findByCompany_CompanyName("companyName", pageable);
//
//        assertThat(byCompanyCompanyName.getContent().get(0).getCompany().getCompanyName());
//    }

}
