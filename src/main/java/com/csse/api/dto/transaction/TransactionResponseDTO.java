package com.csse.api.dto.transaction;

import com.csse.api.enums.TransactionCategory;
import com.csse.api.enums.TransactionMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionResponseDTO {
    private long transactionId;
    private double amount;
    private TransactionCategory transactionCategory;
    private TransactionMethod transactionMethod;
    private Date transactionDate;
    private String description;
    private String residentName;  // Name of the Resident
    private String authorityName; // Name of the WMA (Authority)
}
