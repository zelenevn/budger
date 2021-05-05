package com.data.dto;

import com.data.entity.Account;
import com.data.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionDto {

    private String owner;

    private Float value;

    private String currency;

    private Timestamp transactionTime;

    private String category;

    public TransactionDto(Transaction transaction, Account account) {
        this.owner = account.getSecondUsername();
        this.value = transaction.getValue();
        this.currency = transaction.getCurrency();
        this.transactionTime = transaction.getTransactionTime();
        this.category = transaction.getCategory().getCategoryName();
    }
}
