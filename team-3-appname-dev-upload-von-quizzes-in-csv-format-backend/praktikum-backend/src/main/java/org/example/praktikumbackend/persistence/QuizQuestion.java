package org.example.praktikumbackend.persistence;

import java.util.List;
import java.util.UUID;

public class QuizQuestion {
    private UUID id;
    private String name;
    private List<Question> questions;

    public QuizQuestion(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
