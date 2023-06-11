package com.js.springbootcrmbackend.service;

import com.js.springbootcrmbackend.exception.CRMException;
import com.js.springbootcrmbackend.model.RefreshToken;
import com.js.springbootcrmbackend.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    void valdiateRefreshToken(String token){
        refreshTokenRepository.findByToken(token).orElseThrow(() -> new
                CRMException("Invalid Refresh Token"));
    }

    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
}
