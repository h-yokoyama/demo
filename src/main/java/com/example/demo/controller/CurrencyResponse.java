package com.example.demo.controller;

import java.util.List;

import com.example.demo.domain.Currency;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CurrencyResponse {
	
	private List<Currency> currencies;
}
