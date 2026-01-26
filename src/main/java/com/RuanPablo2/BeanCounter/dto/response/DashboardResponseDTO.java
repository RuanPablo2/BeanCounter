package com.RuanPablo2.BeanCounter.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class DashboardResponseDTO {

    BigDecimal totalIncome;
    BigDecimal totalExpense;
    BigDecimal balance;

    public DashboardResponseDTO(BigDecimal totalIncome, BigDecimal totalExpense, BigDecimal balance) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
    }
}