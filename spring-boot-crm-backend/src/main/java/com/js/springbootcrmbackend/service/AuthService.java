package com.js.springbootcrmbackend.service;

import com.js.springbootcrmbackend.dto.RegisterRequest;
import com.js.springbootcrmbackend.exception.CRMException;
import com.js.springbootcrmbackend.model.NotificationEmail;
import com.js.springbootcrmbackend.model.Role;
import com.js.springbootcrmbackend.model.User;
import com.js.springbootcrmbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
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
                .username(registerRequest.getUsername())
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


}