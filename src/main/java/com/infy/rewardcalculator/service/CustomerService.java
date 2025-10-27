package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Integer customerId);

    Customer getCustomerByName(String customerName) throws Exception;

    List<Reward> getMonthlyRewards(Integer customerId, String customerName, Long startDateMillis, Long endDateMillis);
}
