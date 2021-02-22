package com.klymchuk.school.service;

import com.klymchuk.school.dto.AuthenticationRequest;
import com.klymchuk.school.model.User;
import com.klymchuk.school.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final SecurityService securityService;

    public String login(AuthenticationRequest authenticationRequest) {
        log.info("Logging user with email = " + authenticationRequest.getEmail());

        User user = securityService.getUserByEmail(authenticationRequest.getEmail());

//        if (!encoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
//            log.error("Password not correct");
//            throw new IllegalArgumentException("Password not correct");
//        }
        return jwtTokenProvider.createToken(user);
    }
}
