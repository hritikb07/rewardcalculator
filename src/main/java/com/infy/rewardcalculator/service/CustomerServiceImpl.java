package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.dto.MonthlyReward;
import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Customer;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.infy.rewardcalculator.util.RewardUtil.calculatePoints;
import static com.infy.rewardcalculator.util.RewardUtil.getMonthFromMillis;

@Service
public class CustomerServiceImpl implements CustomerService {
    private  final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer getCustomerById(int customerId) {
        return (Customer) customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
    }

    @Override
    public List<Reward> getMonthlyRewards(int customerId, Long startDateMillis, Long endDateMillis) {
        Customer customer=(Customer)customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));;
        List<Transaction> transactionList=customer.getTransactions();
        // Convert Iterable to Stream
        Stream<Transaction> transactionStream = StreamSupport.stream(transactionList.spliterator(), false).filter(t -> {
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
        return rewardsMap.entrySet().stream().map(entry -> new Reward(entry.getKey(), entry.getValue().entrySet().stream().map(monthEntry -> new MonthlyReward(monthEntry.getKey(), monthEntry.getValue())).collect(Collectors.toList()))).collect(Collectors.toList());

    }
}
