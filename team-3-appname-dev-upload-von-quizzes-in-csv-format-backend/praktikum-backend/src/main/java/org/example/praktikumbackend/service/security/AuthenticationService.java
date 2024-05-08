package org.example.praktikumbackend.service.security;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.praktikumbackend.controller.AuthResponse;
import org.example.praktikumbackend.security.jwt.JwtCookieConfig;
import org.example.praktikumbackend.security.jwt.JwtUtil;
import org.example.praktikumbackend.security.jwt.KeyStoreManager;
import org.example.praktikumbackend.security.jwt.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.util.*;

@Service
@Component
@Slf4j
public class AuthenticationService implements UserDetailsService {
 
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtCookieConfig cookieConfig;
    @Autowired
    KeyStoreManager keyStoreManager;



    public ResponseEntity<AuthResponse> authenticateUser(String authorization, HttpServletResponse response) throws Exception {
        // decodes request header and splits into username/pw
        String headerString = authorization.substring("Basic".length()).trim();
        String decoded = new String(Base64.getDecoder().decode(headerString));
        String username = decoded.split(":", 2)[0];
        String password = decoded.split(":", 2)[1];
        log.info("password: " + password);

        if(username.equals("lecturer") && password.equals("lecturer")) {

            final String jwt = jwtUtil.generateToken(UserRole.LECTURER);

            Cookie jwtCookie = cookieConfig.createCookie("jwt", jwt);

            response.addCookie(jwtCookie);

            return new ResponseEntity<>(new AuthResponse(username, "Login Successful"), HttpStatus.OK);

        }
        else if (username.equals("student") && password.equals("student")) {
            final String jwt = jwtUtil.generateToken(UserRole.STUDENT);

            Cookie jwtCookie = cookieConfig.createCookie("jwt", jwt);

            response.addCookie(jwtCookie);

            return new ResponseEntity<>(new AuthResponse(username, "Login Successful"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new AuthResponse("Invalid Credentials!"), HttpStatus.UNAUTHORIZED);
        }


    }

    public void setAuthenticationToken(Authentication auth) {
        SecurityContextHolder.getContext().setAuthentication(auth);
    }


    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Simply create a new jwt cookie which overwrites the previous one, if it exists
        Cookie deletedCookie = cookieConfig.createCookie("jwt", "deleted");
        deletedCookie.setMaxAge(0);
        response.addCookie(deletedCookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    public String getUserRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
