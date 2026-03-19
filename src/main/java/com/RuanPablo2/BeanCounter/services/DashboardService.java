package com.RuanPablo2.BeanCounter.services;

import com.RuanPablo2.BeanCounter.dto.response.DashboardResponseDTO;
import com.RuanPablo2.BeanCounter.entity.enums.TransactionType;
import com.RuanPablo2.BeanCounter.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DashboardService {

    @Autowired
    private TransactionRepository transactionRepository;

    public DashboardResponseDTO getSummaryByDateRange(Long userId, LocalDate startDate, LocalDate endDate) {

        BigDecimal totalIncome = transactionRepository.sumByUserIdAndTypeAndDateBetween(
                userId, TransactionType.INCOME, startDate, endDate
        );

        BigDecimal totalExpense = transactionRepository.sumByUserIdAndTypeAndDateBetween(
                userId, TransactionType.EXPENSE, startDate, endDate
        );

        // If the SUM value from the bank is null (zero transactions), it is set to zero
        if (totalIncome == null) totalIncome = BigDecimal.ZERO;
        if (totalExpense == null) totalExpense = BigDecimal.ZERO;

        BigDecimal balance = totalIncome.subtract(totalExpense);

        return new DashboardResponseDTO(totalIncome, totalExpense, balance);
    }
}