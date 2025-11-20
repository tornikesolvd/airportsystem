package com.solvd.airportsystem.pattern.factory;

public class ParserFactory {

    public enum ParserType {
        JACKSON,
        JAXB,
        STAX
    }

    public static Parser createParser(ParserType type) {
        switch (type) {
            case JACKSON:
                return new JacksonParserImpl();
            case JAXB:
                return new JAXBParserImpl();
            case STAX:
                return new StAXParserImpl();
            default:
                throw new IllegalArgumentException("Unknown parser type: " + type);
        }
    }
}

