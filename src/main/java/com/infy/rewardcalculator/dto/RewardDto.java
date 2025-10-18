package com.infy.rewardcalculator.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RewardDto {
    private String customerName;
    private List<MonthlyRewardDto> monthlyRewardDtos;

    public RewardDto(String customerName, List<MonthlyRewardDto> monthlyRewardDtos) {
        this.customerName = customerName;
        this.monthlyRewardDtos = monthlyRewardDtos;
    }
}
