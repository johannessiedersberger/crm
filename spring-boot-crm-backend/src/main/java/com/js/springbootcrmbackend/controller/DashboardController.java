package com.js.springbootcrmbackend.controller;

import com.js.springbootcrmbackend.dto.CustomerDto;
import com.js.springbootcrmbackend.service.CustomerService;
import com.js.springbootcrmbackend.service.DashboardService;
import com.js.springbootcrmbackend.service.DealService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    @GetMapping("revenue/{year}/{quarter}")
    public ResponseEntity<Double> getCustomerById(@PathVariable("year") int year, @PathVariable("quarter") int quarter){
        return status(HttpStatus.OK).body(dashboardService.getDealAmountFromQuarter(year, quarter));
    }
}
