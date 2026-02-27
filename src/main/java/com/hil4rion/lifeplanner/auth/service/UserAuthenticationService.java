package com.hil4rion.lifeplanner.auth.service;

import com.hil4rion.lifeplanner.auth.dto.AuthTokens;
import com.hil4rion.lifeplanner.auth.model.SecurityUser;
import com.hil4rion.lifeplanner.auth.model.User;
import com.hil4rion.lifeplanner.auth.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;
    private final JwtService jwtService;

    public AuthTokens authenticate(final String username, final String password) {
        final UsernamePasswordAuthenticationToken authToken =
                UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        final Authentication authentication = authenticationManager.authenticate(authToken);

        final SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        final User user = principal.getUser();

        return generateTokens(user);
    }

    private AuthTokens generateTokens(final User user) {
        final String accessToken = jwtService.generateToken(user.getUsername());
        return new AuthTokens(accessToken);
    }

}
