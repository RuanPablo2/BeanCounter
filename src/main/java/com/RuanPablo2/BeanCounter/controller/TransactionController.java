package com.RuanPablo2.BeanCounter.controller;

import com.RuanPablo2.BeanCounter.dto.request.TransactionRequestDTO;
import com.RuanPablo2.BeanCounter.dto.response.TransactionResponseDTO;
import com.RuanPablo2.BeanCounter.security.CustomUserDetails;
import com.RuanPablo2.BeanCounter.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid TransactionRequestDTO request) {

        TransactionResponseDTO response = transactionService.create(request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Example /transactions?month=1&year=2025
    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> list(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year
    ) {
        // If you don't include the month/year in the URL, it will retrieve the current date
        if (month == null) month = LocalDate.now().getMonthValue();
        if (year == null) year = LocalDate.now().getYear();

        List<TransactionResponseDTO> transactions = transactionService.listByMonth(userDetails.getId(), month, year);
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> update(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id,
            @RequestBody @Valid TransactionRequestDTO request) {

        TransactionResponseDTO response = transactionService.update(id, request, userDetails.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id) {

        transactionService.delete(id, userDetails.getId());
        return ResponseEntity.noContent().build();
    }
}