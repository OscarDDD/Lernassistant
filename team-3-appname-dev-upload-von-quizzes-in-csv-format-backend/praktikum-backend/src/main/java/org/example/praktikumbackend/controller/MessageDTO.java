package org.example.praktikumbackend.controller;

import org.example.praktikumbackend.persistence.Message;

public record MessageDTO(String content) {

    Message toMessage() {
        return new Message(content());
    }

    public static MessageDTO fromMessage(Message message) {
        return new MessageDTO(message.getContent());
    }
}