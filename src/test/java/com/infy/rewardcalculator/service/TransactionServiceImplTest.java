package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.dto.MonthlyRewardDto;
import com.infy.rewardcalculator.dto.RewardDto;
import com.infy.rewardcalculator.entity.Customer;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionServiceImplTest {

    @Test
    void testGetMonthlyReward() {

        TransactionRepository transactionRepository = mock(TransactionRepository.class);

        TransactionServiceImpl transactionServiceImpl = new TransactionServiceImpl(transactionRepository);

        Transaction transcation = new Transaction();

        Customer customer = new Customer();

        customer.setCustomerId(1000);

        customer.setCustomerName("Hritik");

        transcation.setCustomer(customer);

        transcation.setTransactionAmount(120);

        transcation.setTransactionDate(1758269121036L);

        Transaction transcation1 = new Transaction();

        Customer customer1 = new Customer();

        customer1.setCustomerId(1000);

        customer1.setCustomerName("Hritik");

        transcation1.setCustomer(customer1);

        transcation1.setTransactionAmount(200);

        transcation1.setTransactionDate(1758269121036L);

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(transcation);

        transactions.add(transcation1);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<RewardDto> rewardData = transactionServiceImpl.getMonthlyRewards();

        // Assert only 1 customer
        Assertions.assertEquals(1, rewardData.size());

        // Get the RewardDto for "Hritik"
        RewardDto hritikReward = rewardData.stream().filter(r -> "Hritik".equals(r.getCustomerName())).findFirst().orElseThrow(() -> new AssertionError("Customer Hritik not found"));

        // Assert that there is a reward for SEPTEMBER and its value is 340
        int septemberReward = hritikReward.getMonthlyRewardDtos().stream().filter(m -> "SEPTEMBER".equals(m.getMonth())).findFirst().map(MonthlyRewardDto::getRewardAmount).orElseThrow(() -> new AssertionError("SEPTEMBER reward not found"));

        Assertions.assertEquals(340, septemberReward);

    }


    @Test
    void testZeroGetMonthlyReward() {

        TransactionRepository transctionRepository = mock(TransactionRepository.class);

        TransactionServiceImpl transcationServiceImpl = new TransactionServiceImpl(transctionRepository);


        Transaction transcation = new Transaction();

        Customer customer = new Customer();

        customer.setCustomerId(1000);

        customer.setCustomerName("Hritik");

        transcation.setCustomer(customer);

        transcation.setTransactionAmount(45);

        transcation.setTransactionDate(1758269121036L);

        Transaction transcation1 = new Transaction();

        Customer customer1 = new Customer();

        customer1.setCustomerId(1000);

        customer1.setCustomerName("Hritik");

        transcation1.setCustomer(customer1);

        transcation1.setTransactionAmount(20);

        transcation1.setTransactionDate(1758269121036L);

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(transcation);

        transactions.add(transcation1);

        when(transctionRepository.findAll()).thenReturn(transactions);

        List<RewardDto> rewardData = transcationServiceImpl.getMonthlyRewards();


        // Assert only 1 customer
        Assertions.assertEquals(1, rewardData.size());

        // Get the RewardDto for "Hritik"
        RewardDto hritikReward = rewardData.stream().filter(r -> "Hritik".equals(r.getCustomerName())).findFirst().orElseThrow(() -> new AssertionError("Customer Hritik not found"));

        // Assert that there is a reward for SEPTEMBER and its value is 0
        int septemberReward = hritikReward.getMonthlyRewardDtos().stream().filter(m -> "SEPTEMBER".equals(m.getMonth())).findFirst().map(MonthlyRewardDto::getRewardAmount).orElseThrow(() -> new AssertionError("SEPTEMBER reward not found"));

        Assertions.assertEquals(0, septemberReward);

    }
}