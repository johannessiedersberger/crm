package com.js.springbootcrmbackend.controller;

import com.js.springbootcrmbackend.dto.CustomerDto;
import com.js.springbootcrmbackend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return status(HttpStatus.OK).body(customerService.getAllCustomers());
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long customerId){
        return status(HttpStatus.OK).body(customerService.getCustomerbyId(customerId));
    }

    @PostMapping("/customers")
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDto customerRequest){
        customerService.save(customerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerDto customerRequest){
        customerService.updateCustomer(customerId, customerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long customerId){
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
