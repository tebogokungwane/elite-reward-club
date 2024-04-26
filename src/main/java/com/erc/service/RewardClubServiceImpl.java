package com.erc.service;

import com.erc.entity.CustomerReward;
import com.erc.repository.CompanyRepository;
import com.erc.repository.CustomerRewardRepository;
import com.erc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Slf4j
public class RewardClubServiceImpl implements RewardClubService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRewardRepository customerRewardRepository;

//    @Override
//    @Transactional
//    public void createCustomer(User customer) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//
//        CustomerReward customerReward = CustomerReward
//                .builder()
//                .customerRewardEmailAddress(customer.getEmailAddress())
//                .rewardPointToIncrement(0)
//                .setRewardPoints(6)
//                .localDateTime(localDateTime)
//                .build();
//
//        User customerEntity = User
//                .builder()
//                .firstName(customer.getFirstName())
//                .lastName(customer.getLastName())
//                .emailAddress(customer.getEmailAddress())
//                .phoneNumber(customer.getPhoneNumber())
//                .dateCreated(localDateTime)
//                        .role(customer.getRole())
//                                .companyList(customer.getCompanyList())
//                                        .customerReward(customerReward)
//                                                .build();
//
//        BeanUtils.copyProperties(customer,customerEntity);
//        userRepository.save(customerEntity);
//    }


//
//    @Override
//    public Page<User> getAllUsers(Pageable pageable) {
//        if(pageable == null){
//            pageable = Pageable.unpaged();
//        }
//        return userRepository.findAll(pageable);
//    }

//    @Override
//    public List<Company> findAllCompanies() {
//        List<Company> CompanyList = companyRepository.findAll();
//        if (CompanyList.isEmpty()){
//            return Collections.emptyList();
//        }
//        return CompanyList;
//    }

//    @Override
//    public List<User> getAllByRoleAndCompanyList_CompanyName(Role role, String companyName) {
//        List<User> allByRoleAndCompanyListCompanyName = userRepository.getAllByRoleAndCompanyList_CompanyName(role, companyName);
//        if(allByRoleAndCompanyListCompanyName.isEmpty()){
//            return Collections.emptyList();
//        }
//        return allByRoleAndCompanyListCompanyName;
//    }

    @Override
    public CustomerReward getRewardCardByEmail(String emailAddress) {
        CustomerReward byCustomerRewardEmailAddress = customerRewardRepository.findByCustomerRewardEmailAddress(emailAddress);
        if(emailAddress == null || emailAddress.isEmpty()){
            throw  new IllegalArgumentException("Email cannot be empty");
        }
        if(byCustomerRewardEmailAddress == null){
            throw new NumberFormatException("Reward card not found");
        }
        return byCustomerRewardEmailAddress;
    }

    @Override
    public List<CustomerReward> getAllRewardCustomers() {
        List<CustomerReward> rewardEntityList = customerRewardRepository.findAll();
        if(rewardEntityList.isEmpty()){
            return Collections.emptyList();
        }
         return rewardEntityList;
    }
//    @Override
//    public boolean deleteCustomer(long id) {
//        User byId = userRepository.findById((int) id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
//        userRepository.delete(byId);
//        return true;
//    }

//    @Override
//    public User updateUser(long id, User customer) {
//        Optional<User> customerById = userRepository.findById((int) id);
//        if(customerById.isEmpty()){
//            throw new IllegalArgumentException("Customer not found");
//        }
//        User Customer = customerById.get();
//        Customer.setFirstName(customer.getFirstName());
//        Customer.setLastName(customer.getLastName());
//        Customer.setPhoneNumber(customer.getPhoneNumber());
//        userRepository.save(Customer);
//        return Customer;
//    }

//    @Override
//    public Set<String> getAllCompanyName() {
//
//        List<Company> companyNameList = companyRepository.findAll();
//        if(companyNameList.isEmpty()){
//            throw new NoSuchElementException("No Companies found");
//        }
//        Set<String> collectCompanyName = companyNameList.stream()
//                .map(Company::getCompanyName)
//                .collect(Collectors.toSet());
//
//        return collectCompanyName;
//    }
}
