package com.js.springbootcrmbackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.thymeleaf.TemplateEngine;

@Configuration
public class Config {

    @Bean
    public TemplateEngine templateEngine() {
        return new TemplateEngine();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
