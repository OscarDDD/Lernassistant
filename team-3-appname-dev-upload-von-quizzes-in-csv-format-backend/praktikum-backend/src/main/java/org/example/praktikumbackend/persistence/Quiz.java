package org.example.praktikumbackend.persistence;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;
import java.util.List;


@Entity
public class Quiz {
    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    private Instant createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id") // This column will be added to 'Question' table for the foreign key
    private List<Question> questions;
    private String name;
    private boolean isActive;

    public Quiz(List<Question> questions, String name, boolean isActive) {this.questions = questions; this.name = name; this.isActive = isActive;}

    public Quiz() {}

    // getter
    public UUID getId() {
        return id;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public String getName() {return name; }
    public boolean getIsActive() {return isActive; }
    // setter
    public void setIsActive(boolean isActive) {this.isActive = isActive;}
}
