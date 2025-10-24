package com.solvd.airportsystem.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.math.BigDecimal;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

    @Override
    public BigDecimal unmarshal(String value) throws Exception {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return new BigDecimal(value);
    }

    @Override
    public String marshal(BigDecimal value) throws Exception {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}


