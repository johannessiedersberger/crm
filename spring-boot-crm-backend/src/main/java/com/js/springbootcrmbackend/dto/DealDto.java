package com.js.springbootcrmbackend.dto;

import com.js.springbootcrmbackend.model.Customer;
import com.js.springbootcrmbackend.model.DealStage;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealDto {
    private Long dealId;
    private Long customerId;
    private String name;
    private double amount;
    private DealStage dealStage;
    private Date createdDate;
    private Date closedDate;

}
