package com.solvd.airportsystem.pattern.factory;

import com.solvd.airportsystem.domain.Airport;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JAXBParserImpl implements Parser {

    @Override
    public Airport parse(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Airport.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Airport) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException("JAXB parsing failed", e);
        }
    }
}

