package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.entity.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionService {

    List<Transaction> getAllTransactions();
    void saveTransaction(Transaction transaction);
    Map<String, Map<String, Integer>> getMonthlyRewards();
}
