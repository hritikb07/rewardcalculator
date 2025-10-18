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
import java.util.stream.Stream;
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
    public List<RewardDto> getMonthlyRewards(Long startDateMillis, Long endDateMillis) {
        Iterable<Transaction> transactions = transactionRepository.findAll();

        // Convert Iterable to Stream
        Stream<Transaction> transactionStream = StreamSupport.stream(transactions.spliterator(), false).filter(t -> {
            // If dates are provided, apply filter; otherwise, always include the transaction
            if (startDateMillis != null && endDateMillis != null) {
                long txnDate = t.getTransactionDate(); // Assuming transactionDate is in millis
                return txnDate >= startDateMillis && txnDate <= endDateMillis;
            }
            return true;
        });

        // Grouping transactions by customer name and month, summing points
        Map<String, Map<String, Integer>> rewardsMap = transactionStream.collect(Collectors.groupingBy(t -> t.getCustomer().getCustomerName(), Collectors.groupingBy(t -> getMonthFromMillis(t.getTransactionDate()), Collectors.summingInt(t -> calculatePoints(t.getTransactionAmount())))));

        // Mapping to List<RewardDto>
        return rewardsMap.entrySet().stream().map(entry -> new RewardDto(entry.getKey(), entry.getValue().entrySet().stream().map(monthEntry -> new MonthlyRewardDto(monthEntry.getKey(), monthEntry.getValue())).collect(Collectors.toList()))).collect(Collectors.toList());
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
