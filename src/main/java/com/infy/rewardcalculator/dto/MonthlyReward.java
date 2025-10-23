package com.infy.rewardcalculator.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonthlyReward {

    private String month;
    private int rewardAmount;

    public MonthlyReward(String key, Integer value) {
        this.month = key;
        this.rewardAmount = value;
    }
}
