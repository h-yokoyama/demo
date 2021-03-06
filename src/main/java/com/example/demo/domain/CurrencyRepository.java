package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}