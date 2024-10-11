package com.csse.api.dto.transaction;

import com.csse.api.enums.TransactionCategory;
import com.csse.api.enums.TransactionMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionRequestDTO {
    private double amount;
    private TransactionCategory transactionCategory;
    private TransactionMethod transactionMethod;
    private Date transactionDate;
    private String description;
    private long residentId;  // ID of the Resident
    private long authorityId;  // ID of the WMA
}
