package com.js.springbootcrmbackend.repository;

import com.js.springbootcrmbackend.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {

}
