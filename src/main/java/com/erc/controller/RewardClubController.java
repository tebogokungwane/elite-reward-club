package com.erc.controller;

import com.erc.entity.CustomerReward;
import com.erc.service.RewardClubService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
//@PreAuthorize("hasRole('Admin')")
public class RewardClubController {

    @Autowired
    private RewardClubService rewardClubService;

    @GetMapping("/getRewardCardByEmail/{emailAddress}")
//    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<CustomerReward> getRewardCardByEmail(@PathVariable String emailAddress) {
        return ResponseEntity.ok(rewardClubService.getRewardCardByEmail(emailAddress));
    }

    @GetMapping("/getAllRewardCardHolders/")
//    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<CustomerReward>> getAllRewardCardHolders() {
        return ResponseEntity.ok(rewardClubService.getAllRewardCustomers());
    }
}
