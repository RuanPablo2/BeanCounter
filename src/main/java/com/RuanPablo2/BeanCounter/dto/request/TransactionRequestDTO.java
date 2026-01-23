package com.RuanPablo2.BeanCounter.dto.request;

import com.RuanPablo2.BeanCounter.entity.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequestDTO {

    @NotBlank(message = "Description is required")
    String description;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    BigDecimal amount;

    @NotNull(message = "Date is required")
    LocalDate date;

    @NotNull(message = "Type is required")
    TransactionType type;

    @NotNull(message = "User ID is required")
    Long userId;
}