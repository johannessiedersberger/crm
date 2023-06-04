package com.js.springbootcrmbackend.service;

import com.js.springbootcrmbackend.dto.CustomerDto;
import com.js.springbootcrmbackend.model.Customer;
import com.js.springbootcrmbackend.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerService {

    @Autowired
    private ModelMapper modelMapper;

    private final CustomerRepository customerRepository;

    public void save(CustomerDto customerDto){
        customerRepository.save(modelMapper.map(customerDto, Customer.class));
    }

    public List<CustomerDto> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
    }

    public CustomerDto getCustomerbyId(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(customer, CustomerDto.class);
    }

    public void updateCustomer(Long customerId, CustomerDto customerDto){
        Customer customer = customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);

        customerRepository.delete(customer);
    }
}
