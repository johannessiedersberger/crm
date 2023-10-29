package com.js.springbootcrmbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Email
    @NotEmpty(message = "Email is required")
    @JsonProperty("email")
    private String email;
    @NotBlank(message = "Password is required")
    @JsonProperty("password")
    private String password;
    @NotBlank(message = "Password2 is required")
    @JsonProperty("password2")
    private String password2;
}
