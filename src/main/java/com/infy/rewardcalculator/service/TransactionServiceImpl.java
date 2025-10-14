package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    @Override
    public void saveTransaction(Transaction transaction) {
            transactionRepository.save(transaction);
    }

    @Override
    public Map<String, Map<String, Integer>> getMonthlyRewards() {

        Iterable<Transaction> transactions = transactionRepository.findAll();
        Map<String, Map<String, Integer>> rewards = new HashMap<>();
        for( Transaction transaction : transactions) {
            String customerName = transaction.getCustomer().getCustomerName();
            String month = getMonthFromMillis(transaction.getTransactionDate());
            int points = calculatePoints(transaction.getTransactionAmount());
            rewards.computeIfAbsent(customerName, k-> new HashMap<>()).merge(month, points, Integer::sum);
        }
        return rewards;
    }

    public String getMonthFromMillis(long millis) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        Month month = localDateTime.getMonth();
        return month.toString();
    }

    public int calculatePoints(double amount) {
        int points = 0;
        if(amount > 100) {
            points += (int) ((amount-100)*2);
            points += 50;
        }
        if(amount >= 50 && amount <=100) {
            points += (int) (amount - 50);
        }
        return points;
    }
}
