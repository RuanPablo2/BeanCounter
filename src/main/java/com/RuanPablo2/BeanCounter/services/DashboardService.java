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

    public DashboardResponseDTO getMonthlySummary(Long userId, int month, int year) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        BigDecimal totalIncome = transactionRepository.sumByUserIdAndTypeAndDateBetween(
                userId, TransactionType.INCOME, startDate, endDate
        );

        BigDecimal totalExpense = transactionRepository.sumByUserIdAndTypeAndDateBetween(
                userId, TransactionType.EXPENSE, startDate, endDate
        );

        BigDecimal balance = totalIncome.subtract(totalExpense);

        return new DashboardResponseDTO(totalIncome, totalExpense, balance);
    }
}