package com.RuanPablo2.BeanCounter.controller;

import com.RuanPablo2.BeanCounter.dto.request.TransactionRequestDTO;
import com.RuanPablo2.BeanCounter.dto.response.TransactionResponseDTO;
import com.RuanPablo2.BeanCounter.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions") // Define a URL base
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@RequestBody @Valid TransactionRequestDTO request) {
        TransactionResponseDTO response = transactionService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Example /transactions?userId=1&month=1&year=2025
    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> list(@RequestParam Long userId, @RequestParam int month, @RequestParam int year) {
        List<TransactionResponseDTO> transactions = transactionService.listByMonth(userId, month, year);
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TransactionRequestDTO request) {
        TransactionResponseDTO response = transactionService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}