package com.infy.rewardcalculator.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonthlyRewardDto {

    private String month;
    private int rewardAmount;

    public MonthlyRewardDto(String key, Integer value) {
        this.month = key;
        this.rewardAmount = value;
    }
}
