package com.js.springbootcrmbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${jwt.public.key}")
    RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    RSAPrivateKey privateKey;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/customers")
                        .permitAll()

        )
    }


}
