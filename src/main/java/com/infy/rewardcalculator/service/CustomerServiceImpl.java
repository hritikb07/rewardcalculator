package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Customer;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.infy.rewardcalculator.util.RewardUtil.getRewards;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
    }

    @Override
    public Customer getCustomerByName(String customerName) throws Exception {
        return customerRepository.findByCustomerName(customerName).orElseThrow(() -> new Exception("Customer not found with name: " + customerName));
    }

    @Override
    public List<Reward> getMonthlyRewards(Integer customerId, String customerName, Long startDateMillis, Long endDateMillis) {
        System.out.println(customerId);
        System.out.println(customerName);
        Customer customer;
        if (customerName != null && !customerName.isBlank()) {
            customer = customerRepository.findByCustomerName(customerName).orElseThrow(() -> new RuntimeException("Customer not found with name: " + customerName));
        } else {
            customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        }
        List<Transaction> transactionList = customer.getTransactions();
        return getRewards(startDateMillis, endDateMillis, transactionList);
    }
}
