package com.js.springbootcrmbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dealId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    private String name;
    private double amount;

    @Enumerated(EnumType.STRING)
    private DealStage dealStage;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    private Date closedDate;


}
