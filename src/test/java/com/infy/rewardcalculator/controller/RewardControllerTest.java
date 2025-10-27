package com.infy.rewardcalculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.rewardcalculator.dto.MonthlyReward;
import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Customer;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.service.CustomerService;
import com.infy.rewardcalculator.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardController.class)
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    // Sample DTOs
    private Reward sampleRewardDto() {
        MonthlyReward monthly = new MonthlyReward();
        monthly.setMonth("SEPTEMBER");
        monthly.setRewardAmount(150);

        Reward reward = new Reward();
        reward.setCustomerName("Hritik");
        reward.setMonthlyRewards(Collections.singletonList(monthly));

        return reward;
    }

    private Transaction sampleTransaction() {
        Transaction txn = new Transaction();
        txn.setTransactionId(1);
        txn.setTransactionAmount(200.0);
        txn.setTransactionDate("2025-27-10");

        Customer customer = new Customer();
        customer.setCustomerId(1000);
        customer.setCustomerName("Hritik");
        txn.setCustomer(customer);

        return txn;
    }

    @Test
    void testRewardsWithEmptyDate() throws Exception {
        List<Reward> mockResponse = Collections.singletonList(sampleRewardDto());
        Mockito.when(transactionService.getMonthlyRewards(null, null)).thenReturn(mockResponse);

        mockMvc.perform(get("/rewards")).andExpect(status().isOk()).andExpect(jsonPath("$[0].customerName").value("Hritik")).andExpect(jsonPath("$[0].monthlyRewards[0].month").value("SEPTEMBER")).andExpect(jsonPath("$[0].monthlyRewards[0].rewardAmount").value(150));
    }

    @Test
    void testRewardsWithDates() throws Exception {
        List<Reward> mockResponse = Collections.singletonList(sampleRewardDto());

        String startDate = "2025-02-15"; // 15 Feb 2025
        String endDate = "2025-04-15";   // 15 Apr 2025

        Mockito.when(transactionService.getMonthlyRewards(any(), any())).thenReturn(mockResponse);

        mockMvc.perform(get("/rewards?startDate=" + startDate + "&endDate=" + endDate)).andExpect(status().isOk()).andExpect(jsonPath("$[0].customerName").value("Hritik")).andExpect(jsonPath("$[0].monthlyRewards[0].month").value("SEPTEMBER")).andExpect(jsonPath("$[0].monthlyRewards[0].rewardAmount").value(150));
    }

    @Test
    void testSaveTransaction() throws Exception {
        Transaction transaction = sampleTransaction();

        mockMvc.perform(post("/transaction").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(transaction))).andExpect(status().isOk()).andExpect(content().string("Transaction saved successfully."));

        Mockito.verify(transactionService).saveTransaction(Mockito.any(Transaction.class));
    }

    @Test
    void testGetAllTransactions() throws Exception {
        List<Transaction> mockTransactions = List.of(sampleTransaction());

        Mockito.when(transactionService.getAllTransactions()).thenReturn(mockTransactions);

        mockMvc.perform(get("/transactions")).andExpect(status().isOk()).andExpect(jsonPath("$[0].transactionAmount").value(200.0)).andExpect(jsonPath("$[0].customer.customerName").value("Hritik"));
    }

    @Test
    void testRewardsWithInvalidPathVariable() throws Exception {
        // Passing non-numeric string instead of long
        mockMvc.perform(get("/rewards/invalidStart/invalidEnd")).andExpect(status().isInternalServerError());
    }

    @Test
    void testSaveTransactionWithMissingFields() throws Exception {
        Transaction invalidTransaction = new Transaction(); // missing fields

        mockMvc.perform(post("/transaction").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(invalidTransaction))).andExpect(status().isInternalServerError()); // Assuming validation is applied

        verify(transactionService, atMostOnce()).saveTransaction(any());
    }

    @Test
    void testGetMonthlyRewardsThrowsException() throws Exception {
        when(transactionService.getMonthlyRewards(any(), any())).thenThrow(new RuntimeException("Something went wrong"));

        mockMvc.perform(get("/rewards")).andExpect(status().isInternalServerError());
    }

    @Test
    void testSaveTransactionThrowsException() throws Exception {
        Transaction txn = sampleTransaction();
        doThrow(new RuntimeException("DB error")).when(transactionService).saveTransaction(any());

        mockMvc.perform(post("/transaction").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(txn))).andExpect(status().isInternalServerError());
    }

    @Test
    void testEmptyTransactionList() throws Exception {
        when(transactionService.getAllTransactions()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/transactions")).andExpect(status().isOk()).andExpect(content().string("[]"));
    }

    @Test
    void testGetCustomerRewards_WithCustomerId_ReturnsRewards() throws Exception {
        Reward reward = new Reward();
        reward.setCustomerName("Hritik");
        List<MonthlyReward> monthlyRewards = new ArrayList<>();
        MonthlyReward monthlyReward = new MonthlyReward();
        monthlyReward.setMonth("January");
        monthlyReward.setRewardAmount(100);
        monthlyRewards.add(monthlyReward);
        reward.setMonthlyRewards(monthlyRewards);
        when(customerService.getMonthlyRewards(101, null, null, null))
                .thenReturn(List.of(reward));

        mockMvc.perform(get("/rewards/customer")
                        .param("customerId", "101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerName").value("Hritik"))
                .andExpect(jsonPath("$[0].monthlyRewards[0].rewardAmount").value(100));
    }

    @Test
    void testGetCustomerRewards_MissingParams_ShouldThrowInfyException() throws Exception {
        mockMvc.perform(get("/rewards/customer"))
                .andExpect(status().isInternalServerError())
                .andExpect(result ->
                        result.getResolvedException().getMessage()
                                .contains("Either customer name or customer Id must be required"));
    }

    @Test
    void testGetCustomerRewards_InvalidDateRange_ShouldThrowException() throws Exception {
        mockMvc.perform(get("/rewards/customer")
                        .param("customerId", "1")
                        .param("startDate", "2025-10-10")
                        .param("endDate", "2025-01-01"))
                .andExpect(status().isInternalServerError());
    }
}
