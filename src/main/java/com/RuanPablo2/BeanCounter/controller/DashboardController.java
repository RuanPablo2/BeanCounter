package com.RuanPablo2.BeanCounter.controller;

import com.RuanPablo2.BeanCounter.dto.response.DashboardResponseDTO;
import com.RuanPablo2.BeanCounter.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // GET /dashboard?userId=1&month=1&year=2025
    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboard(@RequestParam Long userId, @RequestParam int month, @RequestParam int year) {
        DashboardResponseDTO response = dashboardService.getMonthlySummary(userId, month, year);
        return ResponseEntity.ok(response);
    }
}