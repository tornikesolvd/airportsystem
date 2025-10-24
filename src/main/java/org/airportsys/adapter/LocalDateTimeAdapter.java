package org.airportsys.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    
    @Override
    public LocalDateTime unmarshal(String dateTimeString) throws Exception {
        if (dateTimeString == null || dateTimeString.isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(dateTimeString);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.toString();
    }
}

