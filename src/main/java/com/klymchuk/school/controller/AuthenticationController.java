package com.klymchuk.school.controller;

import com.klymchuk.school.dto.AuthenticationRequest;
import com.klymchuk.school.dto.MainStudentDto;
import com.klymchuk.school.dto.MainUserDto;
import com.klymchuk.school.service.AuthService;

import com.klymchuk.school.util.CookieUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("auth/")
@RequiredArgsConstructor
@Api(tags = "Authentication")
public class AuthenticationController {

    @Value("${jwt.token.cookie.name}")
    private String jwtTokenCookieName;

    @Value("${jwt.token.expired}")
    private int jwtTokenCookieExpiredDate;

    private final AuthService authService;

    @PostMapping("login")
    public void login(@Validated @RequestBody AuthenticationRequest authenticationRequest,
                      HttpServletResponse httpServletResponse) {
        String token = authService.login(authenticationRequest);
        CookieUtils.addCookie(httpServletResponse, jwtTokenCookieName, token, jwtTokenCookieExpiredDate);
    }

    @GetMapping("/me")
    MainUserDto me(){
        return authService.currentUser();
    }

    @GetMapping("role")
    public String getUserRole(String token){
        return authService.getUserRole(token);
    }
}
