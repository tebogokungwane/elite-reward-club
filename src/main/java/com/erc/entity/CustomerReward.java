package com.erc.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Column(name = "reward_points_target")
    private long rewardPointsTarget;

    @Column(name = "reward_points_current")
    private long rewardPointCurrent;

    @Column(name = "created_at")
    private LocalDateTime localDateTime;

    @Column(name = "formatted_created_at")
    private String formattedDate;

    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        this.formattedDate = formatDateTime(localDateTime);
        this.lastUpdatedDate = LocalDateTime.now();
    }

    private String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        return localDateTime.format(formatter);
    }
}
