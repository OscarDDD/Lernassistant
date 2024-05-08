package org.example.praktikumbackend.persistence;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    private Instant createdAt;
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public Message() {

    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}