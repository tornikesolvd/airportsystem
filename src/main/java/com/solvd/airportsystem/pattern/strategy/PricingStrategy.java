package com.solvd.airportsystem.pattern.strategy;

import com.solvd.airportsystem.domain.Flight;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Flight flight);
}

