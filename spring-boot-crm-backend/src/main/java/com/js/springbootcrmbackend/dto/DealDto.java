package com.js.springbootcrmbackend.dto;

import com.js.springbootcrmbackend.model.Customer;
import com.js.springbootcrmbackend.model.DealStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealDto {
    private Long dealId;
    private Long customerId;
    private String name;
    private double amount;
    private DealStage dealStage;

}
