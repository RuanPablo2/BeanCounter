package com.RuanPablo2.BeanCounter.dto.request;

import com.RuanPablo2.BeanCounter.entity.Transaction;
import com.RuanPablo2.BeanCounter.entity.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
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

    public TransactionRequestDTO(Transaction entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public @NotBlank(message = "Description is required") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description is required") String description) {
        this.description = description;
    }

    public @NotNull(message = "Amount is required") @Positive(message = "Amount must be positive") BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "Amount is required") @Positive(message = "Amount must be positive") BigDecimal amount) {
        this.amount = amount;
    }

    public @NotNull(message = "Date is required") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "Date is required") LocalDate date) {
        this.date = date;
    }

    public @NotNull(message = "Type is required") TransactionType getType() {
        return type;
    }

    public void setType(@NotNull(message = "Type is required") TransactionType type) {
        this.type = type;
    }

    public @NotNull(message = "User ID is required") Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User ID is required") Long userId) {
        this.userId = userId;
    }
}