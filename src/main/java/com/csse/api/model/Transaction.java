package com.csse.api.model;

import com.csse.api.enums.TransactionCategory;
import com.csse.api.enums.TransactionMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    private double amount;
    private TransactionCategory transactionCategory;
    private TransactionMethod transactionMethod;
    private Date transactionDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident; // Link to Resident entity

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private WMA wma; // Link to WMA entity
}
