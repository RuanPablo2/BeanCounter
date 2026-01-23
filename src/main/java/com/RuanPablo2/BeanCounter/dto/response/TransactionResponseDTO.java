package com.RuanPablo2.BeanCounter.dto.response;

import com.RuanPablo2.BeanCounter.entity.Transaction;
import com.RuanPablo2.BeanCounter.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionResponseDTO {

    Long id;
    String description;
    BigDecimal amount;
    LocalDate date;
    TransactionType type;

    public TransactionResponseDTO(){}

    public TransactionResponseDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.amount = transaction.getAmount();
        this.date = transaction.getDate();
        this.type = transaction.getType();
    }
}