package org.airportsys;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;


public class AirportApp {
    public static void main(String[] args) {
        String xmlFilePath = "src/main/resources/airportsystem.xml";
        String xsdFilePath = "src/main/resources/airportSchema.xml";

        if (validateXMLSchema(xsdFilePath, xmlFilePath)) {
            System.out.println(xmlFilePath + " is valid against " + xsdFilePath);
        } else {
            System.out.println(xmlFilePath + " is not valid against " + xsdFilePath);
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            return false;
        } catch (SAXException e) {
            System.out.println("SAXException: " + e.getMessage());
            return false;
        }
        return true;
    }
}