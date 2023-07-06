package com.js.springbootcrmbackend.model;

import jakarta.persistence.*;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

public class Token {
    @Id
    @GeneratedValue
    private int tokenId;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
