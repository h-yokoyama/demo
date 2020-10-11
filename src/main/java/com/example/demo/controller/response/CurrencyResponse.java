package com.example.demo.controller.response;

import java.util.List;

import com.example.demo.domain.entity.Currency;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CurrencyResponse {
	
	private List<Currency> currencies;
}
