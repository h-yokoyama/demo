package com.example.demo.controller;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyUpdateRequest {

	private Long id;
	
    private String name;

    private String symbol;
    
    private BigDecimal amount;
}
