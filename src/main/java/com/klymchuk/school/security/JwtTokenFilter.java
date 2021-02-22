package com.klymchuk.school.security;

import com.klymchuk.school.util.CookieUtils;
import io.jsonwebtoken.JwtException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Slf4j
public class JwtTokenFilter extends GenericFilterBean {

    @Value("${jwt.token.cookie.name}")
    private String jwtTokenCookieName;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("Starting filter to valid token");

        String token = CookieUtils.getCookieValue((HttpServletRequest) req, "AJWT");
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                log.info("{} cookie was not found", jwtTokenCookieName);
                CookieUtils.deleteCookie(httpServletRequest, httpServletResponse, jwtTokenCookieName);
            }
        } catch (JwtException | AuthenticationException e) {
            CookieUtils.deleteCookie(httpServletRequest, httpServletResponse, jwtTokenCookieName);
        }

        filterChain.doFilter(req, res);
    }

}
