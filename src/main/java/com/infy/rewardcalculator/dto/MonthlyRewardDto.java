package com.infy.rewardcalculator.dto;

import lombok.Data;

@Data
public class MonthlyRewardDto {

    private String month;
    private int rewardAmount;

    public MonthlyRewardDto(String key, Integer value) {
        this.month = key;
        this.rewardAmount = value;
    }
}
