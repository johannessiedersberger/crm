package com.js.springbootcrmbackend.service;

import com.js.springbootcrmbackend.dto.RegisterDto;
import com.js.springbootcrmbackend.exception.CRMException;
import com.js.springbootcrmbackend.model.NotificationEmail;
import com.js.springbootcrmbackend.model.User;
import com.js.springbootcrmbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final MailService mailService;

    public void signUp(RegisterDto registerDto){
        var oldUser = userRepository.findByUsername(registerDto.getEmail()).isEmpty();
        if(oldUser) {
            throw new CRMException("User already exists");
        }

        User User = new User();
        User.setUsername(registerDto.getUsername());
        User.setEmail(registerDto.getEmail());
        User.setPassword(registerDto.getPassword());
        User.setCreated(Instant.now());
        User.setEnabled(false);

        String token = generateVerificationToken();
        User.setUniqueString(token);
        userRepository.save(User);

        mailService.sendMail(new NotificationEmail(
                "Please Activate you Account", User.getEmail(),
                "Click the Link to Active your Account" +
                        "http://localhost:8080/api/auth/accountVerification/" + token

        ));
    }

    private String generateVerificationToken(){
        String token = UUID.randomUUID().toString();
        return token;
    }

    public void verifyAccount(String token){
        User user = userRepository.findByUniqueString(token).orElseThrow(() -> new CRMException("User not Found"));
        user.setEnabled(true);
        userRepository.save(user);
    }
}