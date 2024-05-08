package org.example.praktikumbackend.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.praktikumbackend.security.jwt.JwtUtil;
import org.example.praktikumbackend.service.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class AuthRequestFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationService authService;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        Cookie cookie = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("jwt")) {
                    cookie = c;
                }
            }
        }

        String username = null;
        String jwt = null;

        if (cookie != null) {
            jwt = cookie.getValue();

            if (!jwtUtil.verifyJwt(jwt)) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token Invalid");
                return;
            }
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null ||
                username != null && !username.equals(
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {


            String principal = username;
            String roles = jwtUtil.extractUserRoles(jwt);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal,
                    null, makeRole(roles));
            authService.setAuthenticationToken(token);

            Authentication authContext = SecurityContextHolder.getContext().getAuthentication();
            log.info(String.format("Authenticate Token Set:\n"
                            + "Username: %s\n"
                            + "Password: %s\n"
                            + "Authority: %s\n",
                    authContext.getPrincipal(),
                    authContext.getCredentials(),
                    authContext.getAuthorities().toString()));
        }

        filterChain.doFilter(request, response);
    }

    public Collection<? extends GrantedAuthority> makeRole(String role) {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority(role));

        return list;
    }
}