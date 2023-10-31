package com.js.springbootcrmbackend.service;

import com.js.springbootcrmbackend.exception.CrmException;
import com.js.springbootcrmbackend.model.Deal;
import com.js.springbootcrmbackend.repository.DealRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DashboardService {
    @Autowired
    private final DealRepository dealRepository;

    public double getDealAmountFromQuarter(int year, int quarter) throws CrmException {
        if(quarter > 4 || quarter < 1){
            throw new CrmException("Quarter not valid, must be between 1 and 4");
        }

        List<Deal> allDeals = dealRepository.findAll();

        double amount = 0;

        for(Deal d : allDeals) {
            if(d.getClosedDate() == null)
                continue;

            int currentQuarter = getQuarterFromMonth(d.getClosedDate().getMonth() + 1);
            int currentYear = d.getClosedDate().getYear() + 1900;

            if(currentQuarter == quarter && currentYear == year){
                amount+= d.getAmount();
            }
        }

        return amount;
    }

    private int getQuarterFromMonth(int month) {
        if(month >= 1 && month <= 3){
            return 1;
        }
        else if(month >= 4 && month <= 6){
            return 2;
        }
        else if(month >= 7 && month <= 9){
            return 3;
        }
        else {
            return 4;
        }
    }


}
