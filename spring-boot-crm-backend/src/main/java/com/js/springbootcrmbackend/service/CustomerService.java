package com.js.springbootcrmbackend.service;

import com.js.springbootcrmbackend.dto.CustomerDto;
import com.js.springbootcrmbackend.mapper.CustomerMapper;
import com.js.springbootcrmbackend.model.Customer;
import com.js.springbootcrmbackend.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    private CustomerMapper customerMapper = new CustomerMapper();

    public void save(CustomerDto customerRequest){
        customerRepository.save(customerMapper.convertToEntity(customerRequest));
    }

    public List<CustomerDto> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.convertToDtoList(customers);
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
