package com.solvd.airportsystem.pattern.factory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.airportsystem.domain.Airport;

import java.io.File;

public class JacksonParserImpl implements Parser {

    @Override
    public Airport parse(File file) {
        try {
            ObjectMapper mapper = new ObjectMapper()
                    .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                    .findAndRegisterModules();
            return mapper.readValue(file, Airport.class);
        } catch (Exception e) {
            throw new RuntimeException("Jackson parsing failed", e);
        }
    }
}

