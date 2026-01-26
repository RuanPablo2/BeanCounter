package com.RuanPablo2.BeanCounter.services;

import com.RuanPablo2.BeanCounter.dto.request.TransactionRequestDTO;
import com.RuanPablo2.BeanCounter.dto.response.TransactionResponseDTO;
import com.RuanPablo2.BeanCounter.entity.Transaction;
import com.RuanPablo2.BeanCounter.entity.User;
import com.RuanPablo2.BeanCounter.repository.TransactionRepository;
import com.RuanPablo2.BeanCounter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public TransactionResponseDTO create(TransactionRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User ID not found"));

        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());
        transaction.setType(request.getType());
        transaction.setUser(user);

        transactionRepository.save(transaction);
        return new TransactionResponseDTO(transaction);
    }

    public List<TransactionResponseDTO> listByMonth(Long userId, int month, int year) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(userId, startDate, endDate);

        return transactions.stream()
                .map(TransactionResponseDTO::new)
                .toList();
    }

    public TransactionResponseDTO update(Long id, TransactionRequestDTO request) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + id));

        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());
        transaction.setType(request.getType());

        transactionRepository.save(transaction);

        return new TransactionResponseDTO(transaction);
    }

    public void delete(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new IllegalArgumentException("Transaction not found with ID: " + id);
        }
        transactionRepository.deleteById(id);
    }
}