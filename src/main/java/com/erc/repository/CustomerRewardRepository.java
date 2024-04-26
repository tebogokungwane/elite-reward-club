package com.erc.repository;

import com.erc.entity.CustomerReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRewardRepository extends JpaRepository<CustomerReward,Long> {

   CustomerReward findByCustomerRewardEmailAddress(String emailAddress);
}
