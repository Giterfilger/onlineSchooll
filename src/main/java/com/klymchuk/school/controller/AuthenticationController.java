package com.klymchuk.school.controller;

import com.klymchuk.school.dto.AuthenticationRequest;
import com.klymchuk.school.service.AuthService;

import com.klymchuk.school.util.CookieUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("auth/")
@RequiredArgsConstructor
@Api(tags = "Authentication")
public class AuthenticationController {

    @Value("${jwt.token.cookie.name}")
    private String jwtTokenCookieName;

//    @Value("${jwt.authentication.cookie.expire-date}")
//    private int jwtCookieExpireDate;

    private final AuthService authService;

    @PostMapping("login")
    public void login(@Validated @RequestBody AuthenticationRequest authenticationRequest,
                      HttpServletResponse httpServletResponse) {
        String token = authService.login(authenticationRequest);
        CookieUtils.addCookie(httpServletResponse, jwtTokenCookieName, token, 3600);
    }
}
