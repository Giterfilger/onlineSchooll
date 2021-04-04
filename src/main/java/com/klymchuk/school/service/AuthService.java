package com.klymchuk.school.service;

import com.klymchuk.school.dto.AuthenticationRequest;
import com.klymchuk.school.dto.MainStudentDto;
import com.klymchuk.school.error.exceptions.BadCredentialException;
import com.klymchuk.school.error.exceptions.EntityNotFoundException;
import com.klymchuk.school.model.User;
import com.klymchuk.school.security.JwtTokenProvider;
import com.klymchuk.school.security.JwtUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        if(user == null){
            throw new EntityNotFoundException("Entity with email: " + authenticationRequest.getEmail() + " not found");
        }
        if(!user.getPassword().equals(authenticationRequest.getPassword())){
            throw new BadCredentialException("Password is incorrect");
        }
//        if (!encoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
//            log.error("Password not correct");
//            throw new IllegalArgumentException("Password not correct");
//        }
        return jwtTokenProvider.createToken(user);
    }

    public String getUserRole(String token){
        log.info("Get Role by token: " + token);

        return jwtTokenProvider.getUserRole(token);
    }
}
