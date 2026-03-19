package com.RuanPablo2.BeanCounter.controller;

import com.RuanPablo2.BeanCounter.dto.response.DashboardResponseDTO;
import com.RuanPablo2.BeanCounter.security.CustomUserDetails;
import com.RuanPablo2.BeanCounter.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboard(
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

        DashboardResponseDTO response = dashboardService.getSummaryByDateRange(userDetails.getId(), startDate, endDate);
        return ResponseEntity.ok(response);
    }
}