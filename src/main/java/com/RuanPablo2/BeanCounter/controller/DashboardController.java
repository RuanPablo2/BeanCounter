package com.RuanPablo2.BeanCounter.controller;

import com.RuanPablo2.BeanCounter.dto.response.DashboardResponseDTO;
import com.RuanPablo2.BeanCounter.security.CustomUserDetails;
import com.RuanPablo2.BeanCounter.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // GET /dashboard?month=1&year=2025
    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboard(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year
    ) {
        // If the request does not include the month and year, we use the current date.
        if (month == null) month = LocalDate.now().getMonthValue();
        if (year == null) year = LocalDate.now().getYear();

        Long userId = userDetails.getId();

        DashboardResponseDTO response = dashboardService.getMonthlySummary(userId, month, year);
        return ResponseEntity.ok(response);
    }
}