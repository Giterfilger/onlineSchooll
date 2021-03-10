package com.klymchuk.school.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    Cloudinary getCloudinary(
            @Value("${cloudinary.cloud_name}") String cloud_name,
            @Value("${cloudinary.api_key}") String api_key,
            @Value("${cloudinary.api_secret}") String api_secret){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloud_name,
                "api_key", api_key,
                "api_secret", api_secret));
    }

}
