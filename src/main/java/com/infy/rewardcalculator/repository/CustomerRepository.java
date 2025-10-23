package com.infy.rewardcalculator.repository;

import com.infy.rewardcalculator.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}
