package com.luinel.beprepared.service;

import com.luinel.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.luinel.beprepared.dto.request.AuthenticationRequestForUser;
import com.luinel.beprepared.dto.response.TokenResponse;

public interface AuthenticationService {

    TokenResponse authenticate(AuthenticationRequestForUser authenticationRequest);

    TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequest);
}
