package com.infy.rewardcalculator.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Reward {
    private String customerName;
    private List<MonthlyReward> monthlyRewards;

    public Reward(String customerName, List<MonthlyReward> monthlyRewards) {
        this.customerName = customerName;
        this.monthlyRewards = monthlyRewards;
    }
}
