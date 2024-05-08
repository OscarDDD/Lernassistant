package org.example.praktikumbackend.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private UUID id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    public Question() {}

    // Constructor, getters, and setters
    public UUID getId() {
        return id;
    }
    public String getQuestion() {return question;}
    public String getOptionA() {return optionA;}
    public String getOptionB() {return optionB;}
    public String getOptionC() {return optionC;}
    public String getOptionD() {return optionD;}
    public String getCorrectAnswer() {return correctAnswer;}
}
