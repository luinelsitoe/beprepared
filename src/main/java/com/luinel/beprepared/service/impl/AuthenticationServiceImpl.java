package com.luinel.beprepared.service.impl;

import com.luinel.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.luinel.beprepared.dto.request.AuthenticationRequestForUser;
import com.luinel.beprepared.dto.response.TokenResponse;
import com.luinel.beprepared.model.Citizen;
import com.luinel.beprepared.model.Token;
import com.luinel.beprepared.model.User;
import com.luinel.beprepared.repository.CitizenRepository;
import com.luinel.beprepared.repository.TokenRepository;
import com.luinel.beprepared.repository.UserRepository;
import com.luinel.beprepared.security.JWTService;
import com.luinel.beprepared.service.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final CitizenRepository citizenRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public TokenResponse authenticate(AuthenticationRequestForUser authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            //Isso da um erro
        }
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        saveUserToken(user, token);
        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getPhone(),
                        authenticationRequest.getOtp()
                )
        );
        var citizen = citizenRepository.findByPhone(authenticationRequest.getPhone()).orElseThrow();
        var token = jwtService.generateToken(citizen);
        saveCitizenToken(citizen, token);
        citizen.setOtp(null);
        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    private void saveUserToken(User user, String token) {
        var jwtToken = Token.builder()
                .user(user)
                .token(token)
                .expired(false)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);
    }

    private void saveCitizenToken(Citizen citizen, String token) {
        var jwtToken = Token.builder()
                .citizen(citizen)
                .token(token)
                .expired(false)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);
    }

}
