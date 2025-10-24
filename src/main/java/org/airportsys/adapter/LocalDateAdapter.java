package org.airportsys.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    
    @Override
    public LocalDate unmarshal(String dateString) throws Exception {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        return LocalDate.parse(dateString);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        if (localDate == null) {
            return null;
        }
        return localDate.toString();
    }
}

