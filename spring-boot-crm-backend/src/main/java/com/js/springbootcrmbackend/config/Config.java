package com.js.springbootcrmbackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

import java.util.Properties;

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

    //@Bean
    //public JavaMailSender javaMailSender() {
    //    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    //    return mailSender;
    //}
}
