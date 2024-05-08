package org.example.praktikumbackend.security.jwt;

import jakarta.servlet.http.Cookie;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtCookieConfig {
    public Cookie createCookie(String key, String value){
        Cookie cookie = new Cookie(key, value);

        // set cookie expiration date for 1 hour
        cookie.setMaxAge(60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
}