package com.infy.rewardcalculator.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "TRANSACTION")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private int transactionId;

    @Column(name = "TRANSACTION_AMOUNT")
    private double transactionAmount;

    @Column(name = "TRANSACTION_DATE")
    private Object transactionDate;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;
}
