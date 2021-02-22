package com.klymchuk.school.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public class CookieUtils {

    public static Cookie getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        Cookie cookie = null;
        if (cookies != null) {
            cookie = Arrays.stream(cookies)
                    .filter(c -> c.getName().equals(name))
                    .findFirst()
                    .orElse(null);
        }
        return cookie;
    }

    public static String getCookieValue(HttpServletRequest req, String name) {
        log.info("Get value from cookie by cookie name");
        Cookie cookie = getCookie(req, name);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        response.addHeader(HttpHeaders.SET_COOKIE, ResponseCookie.from(name, value)
                .httpOnly(true)
                .path("/")
//                .sameSite("none")
//                .secure(true)
                .maxAge(maxAge)
                .build().toString());
        log.info("{} cookie was added", name);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    response.addHeader(HttpHeaders.SET_COOKIE, ResponseCookie.from(name, "")
                            .httpOnly(true)
                            .path("/")
                            .sameSite("none")
                            .secure(true)
                            .maxAge(0)
                            .build().toString());

                    log.info("{} cookie was deleted", name);
                }
            }
        }
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(cookie.getValue())));
    }

}
