package com.erc.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer_reward")
public class CustomerReward {

    @Id
    @SequenceGenerator(
            name = "customer_reward_sequence",
            sequenceName = "customer_reward_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_reward_sequence"

    )
    private Long id;
    @Column(name = "email_address", nullable = false)
    private String customerRewardEmailAddress;
    private int setRewardPoints;
    private int rewardPointToIncrement;
    private LocalDateTime localDateTime;

}
