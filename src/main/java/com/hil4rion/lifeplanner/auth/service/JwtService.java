package com.hil4rion.lifeplanner.auth.service;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import com.hil4rion.lifeplanner.auth.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    private final JwtEncoder jwtEncoder;

    public String generateToken(final String username) {
        final var issuedAt = Instant.now();

        final var claimsSet = JwtClaimsSet.builder()
                .subject(username)
                .issuer(jwtProperties.getIssuer())
                .issuedAt(issuedAt)
                .expiresAt(issuedAt.plus(jwtProperties.getAccessTokenTtl()))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

}
