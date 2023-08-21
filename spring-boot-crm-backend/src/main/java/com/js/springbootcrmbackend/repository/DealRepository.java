package com.js.springbootcrmbackend.repository;

import com.js.springbootcrmbackend.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {

}
