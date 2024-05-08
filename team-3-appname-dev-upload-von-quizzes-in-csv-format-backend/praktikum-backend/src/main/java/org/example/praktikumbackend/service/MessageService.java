package org.example.praktikumbackend.service;

import org.example.praktikumbackend.persistence.Message;
import org.example.praktikumbackend.persistence.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getLastMessage() {
        return messageRepository.findTopByOrderByCreatedAtDesc();
    }
}