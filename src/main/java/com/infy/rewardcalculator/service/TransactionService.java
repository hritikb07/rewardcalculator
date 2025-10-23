package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    void saveTransaction(Transaction transaction);

    List<Reward> getMonthlyRewards(Long startDateMillis, Long endDateMillis);
}
