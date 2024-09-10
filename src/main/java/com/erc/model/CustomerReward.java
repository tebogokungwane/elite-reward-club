package com.erc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReward {

    private String customerRewardEmailAddress;
    private long rewardPointsTarget;
    private long rewardPointCurrent;
    private LocalDateTime localDateTime;
}
