package org.example.praktikumbackend.controller;

public class AuthResponse {
    public String email;
    public String statusMessage;

    public AuthResponse(String email, String statusMessage) {
        this.email = email;
        this.statusMessage = statusMessage;
    }

    public AuthResponse(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
