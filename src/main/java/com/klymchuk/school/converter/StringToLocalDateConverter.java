package com.klymchuk.school.converter;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
        return LocalDate.parse(mappingContext.getSource());
    }
}
