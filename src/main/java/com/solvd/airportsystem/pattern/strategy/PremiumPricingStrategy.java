package com.solvd.airportsystem.pattern.strategy;

import com.solvd.airportsystem.domain.Flight;

import java.math.BigDecimal;

public class PremiumPricingStrategy implements PricingStrategy {
    private static final BigDecimal BASE_PRICE = new BigDecimal("500.00");
    private static final BigDecimal PREMIUM_MULTIPLIER = new BigDecimal("1.5");

    @Override
    public BigDecimal calculatePrice(Flight flight) {
        return BASE_PRICE.multiply(PREMIUM_MULTIPLIER);
    }
}

