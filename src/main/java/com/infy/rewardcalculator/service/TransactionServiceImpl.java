package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.dto.MonthlyRewardDto;
import com.infy.rewardcalculator.dto.RewardDto;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<RewardDto> getMonthlyRewards() {
        Iterable<Transaction> transactions = transactionRepository.findAll();

        // Step 1: Collect rewards into Map<String, Map<String, Integer>> using streams
        Map<String, Map<String, Integer>> rewardsMap = StreamSupport.stream(transactions.spliterator(), false).collect(Collectors.groupingBy(t -> t.getCustomer().getCustomerName(), Collectors.groupingBy(t -> getMonthFromMillis(t.getTransactionDate()), Collectors.summingInt(t -> calculatePoints(t.getTransactionAmount())))));

        // Step 2: Convert the nested Map into List<RewardDto>
        return rewardsMap.entrySet().stream().map(entry -> {
            String customerName = entry.getKey();
            Map<String, Integer> monthlyMap = entry.getValue();

            List<MonthlyRewardDto> monthlyRewardDtos = monthlyMap.entrySet().stream().map(monthEntry -> {
                MonthlyRewardDto dto = new MonthlyRewardDto();
                dto.setMonth(monthEntry.getKey());
                dto.setRewardAmount(monthEntry.getValue());
                return dto;
            }).collect(Collectors.toList());

            RewardDto rewardDto = new RewardDto();
            rewardDto.setCustomerName(customerName);
            rewardDto.setMonthlyRewardDtos(monthlyRewardDtos);
            return rewardDto;
        }).collect(Collectors.toList());
    }


    public String getMonthFromMillis(long millis) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        Month month = localDateTime.getMonth();
        return month.toString();
    }

    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * 2);
            points += 50;
        }
        if (amount >= 50 && amount <= 100) {
            points += (int) (amount - 50);
        }
        return points;
    }
}
