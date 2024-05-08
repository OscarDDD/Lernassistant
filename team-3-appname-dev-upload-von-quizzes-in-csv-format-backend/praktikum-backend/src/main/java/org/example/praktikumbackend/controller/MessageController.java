package org.example.praktikumbackend.controller;

import org.example.praktikumbackend.persistence.Message;
import org.example.praktikumbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageDTO messageDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageDTO.fromMessage(messageService.
                        createMessage(messageDTO.toMessage())));
    }

    @GetMapping("/last")
    public ResponseEntity<MessageDTO> getLastMessage() {
        return ResponseEntity
                .ok(MessageDTO.fromMessage(messageService.getLastMessage()));
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity
                .ok(messageService.getAllMessages());
    }
}