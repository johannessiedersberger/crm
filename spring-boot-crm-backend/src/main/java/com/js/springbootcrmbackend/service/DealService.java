package com.js.springbootcrmbackend.service;

import com.js.springbootcrmbackend.dto.DealDto;
import com.js.springbootcrmbackend.model.Customer;
import com.js.springbootcrmbackend.model.Deal;
import com.js.springbootcrmbackend.repository.CustomerRepository;
import com.js.springbootcrmbackend.repository.DealRepository;
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
public class DealService {
    @Autowired
    private ModelMapper modelMapper;

    private final DealRepository dealRepository;
    private final CustomerRepository customerRepository;

    public List<DealDto> getAllDeals(){
        List<Deal> deals = dealRepository.findAll();
        return deals.stream().map(deal -> modelMapper.map(deal, DealDto.class)).collect(Collectors.toList());
    }

    public DealDto getDealById(Long dealId){
        Deal deal = dealRepository.findById(dealId).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(deal, DealDto.class);
    }

    public void saveDeal(DealDto dealDto){
        Customer newCustomer = customerRepository.findById(dealDto.getCustomerId()).orElseThrow(EntityNotFoundException::new);
        var deal = modelMapper.map(dealDto, Deal.class);
        deal.setCustomer(newCustomer);
        dealRepository.save(deal);
    }

    public void updateDeal(Long dealId, DealDto dealDto){
        Deal deal = dealRepository.findById(dealId).orElseThrow(EntityNotFoundException::new);
        Customer newCustomer = customerRepository.findById(dealDto.getCustomerId()).orElseThrow(EntityNotFoundException::new);

        deal.setDealStage(dealDto.getDealStage());
        deal.setCustomer(newCustomer);
        deal.setName(dealDto.getName());
        deal.setAmount(dealDto.getAmount());

        dealRepository.save(deal);
    }

    public void deleteDeal(Long dealId){
        Deal deal = dealRepository.findById(dealId).orElseThrow(EntityNotFoundException::new);
        dealRepository.delete(deal);
    }


}
