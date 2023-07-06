package com.js.springbootcrmbackend.service;

import com.js.springbootcrmbackend.dto.AuthenticationRequest;
import com.js.springbootcrmbackend.dto.AuthenticationResponse;
import com.js.springbootcrmbackend.dto.RegisterRequest;
import com.js.springbootcrmbackend.exception.CRMException;
import com.js.springbootcrmbackend.model.*;
import com.js.springbootcrmbackend.repository.TokenRepository;
import com.js.springbootcrmbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    public void signUp(RegisterRequest registerRequest) {
        var oldUser = !userRepository.findByEmail(registerRequest.getEmail()).isEmpty();
        if(oldUser) {
            throw new CRMException("User already exists");
        }

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .created(Instant.now())
                .enabled(false)
                .role(Role.ADMIN)
                .build();

        String token = generateVerificationToken();
        user.setUniqueString(token);
        userRepository.save(user);

        mailService.sendMail(new NotificationEmail(
                "Please Activate you Account", user.getEmail(),
                "Click the Link to Active your Account: " +
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

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if(validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken){
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }


}