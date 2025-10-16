package com.infy.rewardcalculator.service;

import com.infy.rewardcalculator.entity.Customer;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionServiceImplTest {

    @Test

    void testGetMonthlyReward() {

        TransactionRepository transctionRepository = mock(TransactionRepository.class);

        TransactionServiceImpl transcationServiceImpl = new TransactionServiceImpl(transctionRepository);

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

        when(transctionRepository.findAll()).thenReturn(transactions);

        Map<String, Map<String, Integer>> rewardData = transcationServiceImpl.getMonthlyRewards();



        Assertions.assertEquals(1, rewardData.size());

        Assertions.assertEquals(340, rewardData.get("Hritik").getOrDefault("SEPTEMBER", null));

    }



    @Test

    void testZeroGetMonthlyReward() {

        TransactionRepository transctionRepository = mock(TransactionRepository.class);

        TransactionServiceImpl transcationServiceImpl = new TransactionServiceImpl();

        transcationServiceImpl.setTransactionRepository(transctionRepository);

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

        Map<String, Map<String, Integer>> rewardData = transcationServiceImpl.getMonthlyRewards();



        Assertions.assertEquals(1, rewardData.size());

        Assertions.assertEquals(0, rewardData.get("Hritik").getOrDefault("SEPTEMBER", null));

    }
}