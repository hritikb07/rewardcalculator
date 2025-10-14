package com.infy.rewardcalculator.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "CUSTOMER")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private int customerId;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

}
