package com.js.springbootcrmbackend.service;

import com.js.springbootcrmbackend.dto.AuthenticationDto;
import com.js.springbootcrmbackend.dto.LoginDto;
import com.js.springbootcrmbackend.dto.RefreshTokenDto;
import com.js.springbootcrmbackend.dto.RegisterDto;
import com.js.springbootcrmbackend.exception.CRMException;
import com.js.springbootcrmbackend.model.NotificationEmail;
import com.js.springbootcrmbackend.model.User;
import com.js.springbootcrmbackend.repository.UserRepository;
import com.js.springbootcrmbackend.security.JwTProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwTProvider jwTProvider;
    private final RefreshTokenService refreshTokenService;


    public void signUp(RegisterDto registerDto) {
        var oldUser = userRepository.findByEmail(registerDto.getEmail()).isEmpty();
        if(oldUser) {
            throw new CRMException("User already exists");
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        String token = generateVerificationToken();
        user.setUniqueString(token);
        userRepository.save(user);

        mailService.sendMail(new NotificationEmail(
                "Please Activate you Account", user.getEmail(),
                "Click the Link to Active your Account" +
                        "http://localhost:8080/api/auth/accountVerification/" + token
        ));
    }

    private String generateVerificationToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

    public void verifyAccount(String token) {
        User user = userRepository.findByUniqueString(token).orElseThrow(() -> new CRMException("User not Found"));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwTProvider.generateToken(authentication);
        return AuthenticationDto.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwTProvider.getJwtExpirationInMillis()))
                .email(loginDto.getEmail())
                .build();
    }

    public AuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) {
        refreshTokenService.valdiateRefreshToken(refreshTokenDto.getRefreshToken());
        String token = jwTProvider.generateTokenWithUsername(refreshTokenDto.getEmail());
        return AuthenticationDto.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenDto.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwTProvider.getJwtExpirationInMillis()))
                .email(refreshTokenDto.getEmail())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}