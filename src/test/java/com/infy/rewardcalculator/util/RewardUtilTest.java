package com.infy.rewardcalculator.util;

import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Customer;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.exception.InfyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RewardUtilTest {

    private Transaction txn1, txn2, txn3;
    private Customer customer1, customer2;

    @BeforeEach
    void setup() {
        customer1 = new Customer();
        customer1.setCustomerName("Alice");

        customer2 = new Customer();
        customer2.setCustomerName("Bob");

        txn1 = new Transaction();
        txn1.setCustomer(customer1);
        txn1.setTransactionAmount(120.0);
        txn1.setTransactionDate(Instant.parse("2025-01-10T10:00:00Z").toEpochMilli());

        txn2 = new Transaction();
        txn2.setCustomer(customer1);
        txn2.setTransactionAmount(75.0);
        txn2.setTransactionDate(Instant.parse("2025-02-15T10:00:00Z").toEpochMilli());

        txn3 = new Transaction();
        txn3.setCustomer(customer2);
        txn3.setTransactionAmount(200.0);
        txn3.setTransactionDate(Instant.parse("2025-02-20T10:00:00Z").toEpochMilli());
    }

    // --------------------------
    // getMonthFromMillis()
    // --------------------------
    @Test
    void testGetMonthFromMillis_Positive() throws InfyException {
        long millis = Instant.parse("2025-03-15T00:00:00Z").toEpochMilli();
        String month = RewardUtil.getMonthFromMillis(millis);
        assertEquals("MARCH", month);
    }

    @Test
    void testGetMonthFromMillis_Negative() {
        // Edge case: invalid timestamp (negative value)
        long millis = -1L;
        assertThrows(InfyException.class, () -> {
            RewardUtil.getMonthFromMillis(millis);
        });
    }

    // --------------------------
    // calculatePoints()
    // --------------------------
    @Test
    void testCalculatePoints_PositiveCases() {
        assertEquals(90, RewardUtil.calculatePoints(120.0)); // 50 + (20*2)
        assertEquals(50, RewardUtil.calculatePoints(100.0)); // 50 points
        assertEquals(25, RewardUtil.calculatePoints(75.0));  // 75 - 50 = 25
        assertEquals(0, RewardUtil.calculatePoints(40.0));   // Below threshold
    }

    @Test
    void testCalculatePoints_NegativeOrBoundary() {
        assertEquals(0, RewardUtil.calculatePoints(0));        // Zero transaction
        assertEquals(0, RewardUtil.calculatePoints(-100));     // Negative amount
    }

    // --------------------------
    // getRewards()
    // --------------------------
    @Test
    void testGetRewards_Positive() {
        List<Transaction> transactions = Arrays.asList(txn1, txn2, txn3);

        long start = Instant.parse("2025-01-01T00:00:00Z").toEpochMilli();
        long end = Instant.parse("2025-12-31T23:59:59Z").toEpochMilli();

        List<Reward> rewards = RewardUtil.getRewards(start, end, transactions);

        assertEquals(2, rewards.size());

        Reward aliceReward = rewards.stream().filter(r -> r.getCustomerName().equals("Alice")).findFirst().orElse(null);
        assertNotNull(aliceReward);
        assertEquals(2, aliceReward.getMonthlyRewards().size()); // Jan and Feb
        assertTrue(aliceReward.getMonthlyRewards().stream().anyMatch(m -> m.getMonth().equals("JANUARY")));
        assertTrue(aliceReward.getMonthlyRewards().stream().anyMatch(m -> m.getMonth().equals("FEBRUARY")));

        Reward bobReward = rewards.stream().filter(r -> r.getCustomerName().equals("Bob")).findFirst().orElse(null);
        assertNotNull(bobReward);
        assertEquals(1, bobReward.getMonthlyRewards().size());
    }

    @Test
    void testGetRewards_NullDateRange() {
        List<Transaction> transactions = Arrays.asList(txn1, txn2, txn3);

        // Passing null start and end should include all transactions
        List<Reward> rewards = RewardUtil.getRewards(null, null, transactions);

        assertEquals(2, rewards.size());
    }

    @Test
    void testGetRewards_EmptyTransactions() {
        List<Reward> rewards = RewardUtil.getRewards(null, null, List.of());
        assertTrue(rewards.isEmpty());
    }

    @Test
    void testGetRewards_OutOfRange() {
        List<Transaction> transactions = Arrays.asList(txn1, txn2, txn3);

        long start = Instant.parse("2026-01-01T00:00:00Z").toEpochMilli();
        long end = Instant.parse("2026-12-31T23:59:59Z").toEpochMilli();

        List<Reward> rewards = RewardUtil.getRewards(start, end, transactions);
        assertTrue(rewards.isEmpty());
    }
}
