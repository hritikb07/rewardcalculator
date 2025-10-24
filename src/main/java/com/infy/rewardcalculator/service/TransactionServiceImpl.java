package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.dto.MonthlyReward;
import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.infy.rewardcalculator.util.RewardUtil.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * This API returns all transaction from databases
     *
     * @return List<Transaction>
     */
    @Override
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    /**
     * This API save transaction in database
     *
     * @param transaction
     */
    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    /**
     * This API fetches all transactions from database and filter it based on start and end date. If start and end date not specified
     * then considers all transaction for further processing.
     * Generate Customers monthly rewards
     *
     * @param startDateMillis
     * @param endDateMillis
     * @return list of reward dto
     */
    @Override
    public List<Reward> getMonthlyRewards(Long startDateMillis, Long endDateMillis) {
        Iterable<Transaction> transactions = transactionRepository.findAll();

        return getRewards(startDateMillis, endDateMillis, transactions);
    }
}
