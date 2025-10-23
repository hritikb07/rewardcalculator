package com.infy.rewardcalculator.controller;

import com.infy.rewardcalculator.dto.Reward;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
public class RewardController {

    @Autowired
    private TransactionService transactionService;

//    @GetMapping("/rewards")
//    public ResponseEntity<List<Reward>> rewardsWithEmptyDate() {
//        return getListResponseEntity(null, null);
//    }

    private ResponseEntity<List<Reward>> getListResponseEntity(Long startDate, Long endDate) {
        return new ResponseEntity<>(transactionService.getMonthlyRewards(startDate, endDate), HttpStatus.OK);
    }

    /**
     * Endpoint: GET /rewards
     * Description:
     *   Fetch rewards based on optional start and end dates.
     *   If dates are not provided, they will be passed as null.
     *
     * Example URLs:
     *   GET http://localhost:8080/rewards
     *   Get http://localhost:8080/rewards?startDate=2025-02-15&endDate=2025-09-15
     */
    @RequestMapping(value = "/rewards", method = RequestMethod.GET)
    public ResponseEntity<List<Reward>> getRewards(
            @RequestParam(name = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,

            @RequestParam(name = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Long startMillis = (startDate != null)
                ? startDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                : null;

        Long endMillis = (endDate != null)
                ? endDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                : null;

        List<Reward> rewards = transactionService.getMonthlyRewards(startMillis, endMillis);
        return ResponseEntity.ok(rewards);
    }


    @PostMapping(value = "/transaction", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> saveTransaction(@RequestBody Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return new ResponseEntity<>("Transaction saved successfully.", HttpStatus.OK);
    }

    @GetMapping(value = "/transactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Transaction>> transactions() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }
}
