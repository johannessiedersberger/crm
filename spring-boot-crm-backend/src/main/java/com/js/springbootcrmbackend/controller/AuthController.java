package com.js.springbootcrmbackend.controller;

import com.js.springbootcrmbackend.dto.AuthenticationDto;
import com.js.springbootcrmbackend.dto.LoginDto;
import com.js.springbootcrmbackend.dto.RefreshTokenDto;
import com.js.springbootcrmbackend.dto.RegisterDto;
import com.js.springbootcrmbackend.service.AuthService;
import com.js.springbootcrmbackend.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterDto registerDto) {
        authService.signUp(registerDto);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationDto login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @PostMapping("/refresh/token")
    public AuthenticationDto refreshToken(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        return authService.refreshToken(refreshTokenDto);
    }

    @PostMapping
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        refreshTokenService.deleteRefreshToken(refreshTokenDto.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully");
    }
}
