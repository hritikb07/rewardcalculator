package com.infy.rewardcalculator.controller;

import com.infy.rewardcalculator.entity.Transaction;
import com.infy.rewardcalculator.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RewardController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getreward")
    public ResponseEntity<Map<String, Map<String, Integer>>> getReward() {
        return new ResponseEntity<>(transactionService.getMonthlyRewards(), HttpStatus.OK);
    }

    @PostMapping(value = "/transaction", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Transaction>> saveTransaction(@RequestBody Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping(value = "/get-all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Transaction>> getAll() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }
}
