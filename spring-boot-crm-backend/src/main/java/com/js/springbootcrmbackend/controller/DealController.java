package com.js.springbootcrmbackend.controller;

import com.js.springbootcrmbackend.dto.CustomerDto;
import com.js.springbootcrmbackend.dto.DealDto;
import com.js.springbootcrmbackend.service.DealService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/api/deals")
public class DealController {
    private final DealService dealService;

    @GetMapping
    public ResponseEntity<List<DealDto>> getAllDeals(){
        return status(HttpStatus.OK).body(dealService.getAllDeals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealDto> getDealById(@PathVariable("id") Long dealId){
        return status(HttpStatus.OK).body(dealService.getDealById(dealId));
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody DealDto dealDto){
        dealService.saveDeal(dealDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") Long dealId, @RequestBody DealDto dealRequest){
        dealService.updateDeal(dealId, dealRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long dealId){
        dealService.deleteDeal(dealId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
