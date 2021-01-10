package com.klymchuk.school.config;

import com.klymchuk.school.converter.StringToLocalDateConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RequiredArgsConstructor
@Configuration
public class MapperConfig {

    private final StringToLocalDateConverter stringToLocalDateConverter;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(stringToLocalDateConverter);

        modelMapper.getConfiguration().setAmbiguityIgnored(false);

        return modelMapper;
    }

}
