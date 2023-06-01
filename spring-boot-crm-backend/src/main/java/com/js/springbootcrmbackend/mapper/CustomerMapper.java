package com.js.springbootcrmbackend.mapper;

import com.js.springbootcrmbackend.dto.CustomerDto;
import com.js.springbootcrmbackend.model.Customer;
import org.springframework.beans.BeanUtils;

public class CustomerMapper extends BaseMapper<Customer, CustomerDto>{


    @Override
    public Customer convertToEntity(CustomerDto dto, Object... args) {
        Customer customer = new Customer();
        if(dto != null){
            BeanUtils.copyProperties(dto, customer);
        }
        return customer;
    }

    @Override
    public CustomerDto convertToDto(Customer entity, Object... args) {
        CustomerDto customerDto = new CustomerDto();
        if(entity != null){
            BeanUtils.copyProperties(entity, customerDto);
        }
        return customerDto;
    }
}
