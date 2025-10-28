package com.infy.rewardcalculator.util;

import com.infy.rewardcalculator.dto.MonthlyReward;
import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.exception.InfyException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RewardUtil {

    /**
     * Extract month from provided date
     *
     * @param millis
     * @return month in word
     */
    public static String getMonthFromMillis(long millis) throws InfyException {

        if (millis < 0) {
            throw new InfyException("Date should not be in negative.");
        }
        LocalDateTime localDateTime = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        Month month = localDateTime.getMonth();
        return month.toString();
    }

    /**
     * Calculates reward points according to problem statement
     *
     * @param amount
     * @return reward
     */
    public static int calculatePoints(double amount) {
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

    public static List<Reward> getRewards(Long startDateMillis, Long endDateMillis, Iterable<Transaction> transactions) {
        // Convert Iterable to Stream
        Stream<Transaction> transactionStream = StreamSupport.stream(transactions.spliterator(), false).filter(t -> {
            // If dates are provided, apply filter; otherwise, always include the transaction
            if (startDateMillis != null && endDateMillis != null) {
                long txnDate = (long) t.getTransactionDate(); // Assuming transactionDate is in millis
                return txnDate >= startDateMillis && txnDate <= endDateMillis;
            }
            return true;
        });

        // Grouping transactions by customer name and month, summing points
        Map<String, Map<String, Integer>> rewardsMap = transactionStream.collect(Collectors.groupingBy(t -> t.getCustomer().getCustomerName(), Collectors.groupingBy(t -> {
            try {
                return getMonthFromMillis((Long) t.getTransactionDate());
            } catch (InfyException e) {
                throw new RuntimeException(e);
            }
        }, Collectors.summingInt(t -> calculatePoints(t.getTransactionAmount())))));

        // Mapping to List<RewardDto>
        return rewardsMap.entrySet().stream().map(entry -> new Reward(entry.getKey(), entry.getValue().entrySet().stream().map(monthEntry -> new MonthlyReward(monthEntry.getKey(), monthEntry.getValue())).collect(Collectors.toList()))).collect(Collectors.toList());
    }
}
