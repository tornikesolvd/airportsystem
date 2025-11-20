package com.solvd.airportsystem.pattern.strategy;

import com.solvd.airportsystem.domain.Flight;

import java.math.BigDecimal;

public class TicketPricingService {
    private PricingStrategy pricingStrategy;

    public TicketPricingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public BigDecimal calculateTicketPrice(Flight flight) {
        return pricingStrategy.calculatePrice(flight);
    }
}

