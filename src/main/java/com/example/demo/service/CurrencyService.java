package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Currency;
import com.example.demo.domain.CurrencyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }
    
    public Currency save(String name, String symbol) {
        return currencyRepository.save(Currency.newCurrency(name, symbol));
    }
    
    public void delete(Long id) {
        currencyRepository.findById(id).ifPresent(currency -> currencyRepository.delete(currency));
    }
}