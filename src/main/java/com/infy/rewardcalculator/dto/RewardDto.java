package com.infy.rewardcalculator.dto;

import lombok.Data;

import java.util.List;

@Data
public class RewardDto {
    private String customerName;
    private List<MonthlyRewardDto> monthlyRewardDtos;
}
