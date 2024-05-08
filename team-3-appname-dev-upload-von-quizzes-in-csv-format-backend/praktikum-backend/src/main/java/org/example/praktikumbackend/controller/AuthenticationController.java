package org.example.praktikumbackend.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.praktikumbackend.service.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> loginUnencrypted(@RequestHeader("Authorization") String authorization,
                                                         HttpServletResponse response) throws Exception {
        try {
            return authenticationService.authenticateUser(authorization, response);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthResponse("Invalid Login"), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/testtoken")
    public ResponseEntity<String> checkToken() {
        return ResponseEntity.ok("token valid, user role: " + authenticationService.getUserRole());
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        return authenticationService.logout(request, response);
    }

}
