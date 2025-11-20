package com.solvd.airportsystem.pattern.strategy;

import com.solvd.airportsystem.domain.Flight;

import java.math.BigDecimal;

public class DiscountPricingStrategy implements PricingStrategy {
    private static final BigDecimal BASE_PRICE = new BigDecimal("200.00");
    private static final BigDecimal DISCOUNT_PERCENTAGE = new BigDecimal("0.2");

    @Override
    public BigDecimal calculatePrice(Flight flight) {
        BigDecimal discount = BASE_PRICE.multiply(DISCOUNT_PERCENTAGE);
        return BASE_PRICE.subtract(discount);
    }
}

