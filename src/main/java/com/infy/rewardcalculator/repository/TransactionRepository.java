package com.infy.rewardcalculator.repository;

import com.infy.rewardcalculator.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
