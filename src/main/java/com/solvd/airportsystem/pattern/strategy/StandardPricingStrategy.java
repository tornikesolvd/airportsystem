package com.solvd.airportsystem.pattern.strategy;

import com.solvd.airportsystem.domain.Flight;

import java.math.BigDecimal;

public class StandardPricingStrategy implements PricingStrategy {
    private static final BigDecimal BASE_PRICE = new BigDecimal("200.00");

    @Override
    public BigDecimal calculatePrice(Flight flight) {
        return BASE_PRICE;
    }
}

