package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(int customerId);

    List<Reward> getMonthlyRewards(int customerId, Long startDateMillis, Long endDateMillis);
}
