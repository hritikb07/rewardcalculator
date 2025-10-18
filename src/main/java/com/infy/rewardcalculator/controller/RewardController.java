package com.infy.rewardcalculator.controller;

import com.infy.rewardcalculator.dto.RewardDto;
import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RewardController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/rewards")
    public ResponseEntity<List<RewardDto>> rewardsWithEmptyDate() {
        return getListResponseEntity(null, null);
    }

    private ResponseEntity<List<RewardDto>> getListResponseEntity(Long startDate,Long endDate) {
        return new ResponseEntity<>(transactionService.getMonthlyRewards(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/rewards/{startDate}/{endDate}")
    public ResponseEntity<List<RewardDto>> rewards(@PathVariable(name = "startDate") long startDate, @PathVariable(name = "endDate") long endDate) {
        return getListResponseEntity(startDate, endDate);
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
