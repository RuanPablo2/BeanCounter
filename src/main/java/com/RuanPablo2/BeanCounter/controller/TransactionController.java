package com.RuanPablo2.BeanCounter.controller;

import com.RuanPablo2.BeanCounter.dto.request.TransactionRequestDTO;
import com.RuanPablo2.BeanCounter.dto.response.TransactionResponseDTO;
import com.RuanPablo2.BeanCounter.security.CustomUserDetails;
import com.RuanPablo2.BeanCounter.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> list(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        // Fallback: If no dates are specified, it will take the period from the 1st to the last day of the current month
        if (startDate == null || endDate == null) {
            LocalDate today = LocalDate.now();
            startDate = today.withDayOfMonth(1);
            endDate = today.withDayOfMonth(today.lengthOfMonth());
        }

        List<TransactionResponseDTO> transactions = transactionService.listByDateRange(userDetails.getId(), startDate, endDate);
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