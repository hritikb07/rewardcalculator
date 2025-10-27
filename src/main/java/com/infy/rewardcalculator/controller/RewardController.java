package com.infy.rewardcalculator.controller;

import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.service.CustomerService;
import com.infy.rewardcalculator.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class RewardController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerService customerService;

    private static void validateDateDifference(LocalDate startDate, LocalDate endDate) throws Exception {
        // Validate dates if both are provided
        if (startDate != null && endDate != null) {
            if (endDate.isBefore(startDate)) {
                throw new Exception("End date cannot be before start date.");
            }

            // Check if difference is more than 3 months
            Period period = Period.between(startDate, endDate);
            if (period.toTotalMonths() > 3 || (period.toTotalMonths() == 3 && endDate.getDayOfMonth() > startDate.getDayOfMonth())) {
                throw new Exception("Date range should not exceed 3 months.");
            }
        }
    }

    private ResponseEntity<List<Reward>> getListResponseEntity(Long startDate, Long endDate) {
        return new ResponseEntity<>(transactionService.getMonthlyRewards(startDate, endDate), HttpStatus.OK);
    }

    /**
     * Endpoint: GET /rewards
     * Description:
     * Fetch rewards based on optional start and end dates.
     * If dates are not provided, they will be passed as null.
     * <p>
     * Example URLs:
     * GET <a href="http://localhost:8080/rewards">...</a>
     * Get <a href="http://localhost:8080/rewards?startDate=2025-02-15&endDate=2025-09-15">...</a>
     */
    @RequestMapping(value = "/rewards", method = RequestMethod.GET)
    public ResponseEntity<List<Reward>> getRewards(@RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,

                                                   @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws Exception {


        validateDateDifference(startDate, endDate);

        Long startMillis = (startDate != null) ? startDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli() : null;

        Long endMillis = (endDate != null) ? endDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli() : null;

        List<Reward> rewards = transactionService.getMonthlyRewards(startMillis, endMillis);
        return ResponseEntity.ok(rewards);
    }

    @PostMapping(value = "/transaction", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> saveTransaction(@RequestBody Transaction transaction) {
        if (transaction != null && transaction.getTransactionDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
            LocalDate localDate = LocalDate.parse(transaction.getTransactionDate().toString(), formatter);
            transaction.setTransactionDate(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        transactionService.saveTransaction(transaction);
        return new ResponseEntity<>("Transaction saved successfully.", HttpStatus.OK);
    }

    @GetMapping(value = "/transactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Transaction>> transactions() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @RequestMapping(value = "/rewards/customer", method = RequestMethod.GET)
    public ResponseEntity<List<Reward>> getCustomerRewards(
            @RequestParam(name = "customerId", required = false) Integer customerId,
            @RequestParam(name = "customerName", required = false) String customerName,
            @RequestParam(name = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws Exception {

        validateDateDifference(startDate, endDate);

        Long startMillis = (startDate != null)
                ? startDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                : null;

        Long endMillis = (endDate != null)
                ? endDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                : null;

        // Call service method with all parameters
        List<Reward> rewards = customerService.getMonthlyRewards(customerId, customerName, startMillis, endMillis);
        return ResponseEntity.ok(rewards);
    }
}
