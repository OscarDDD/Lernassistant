package org.example.praktikumbackend.service;

import org.example.praktikumbackend.persistence.Quiz;
import org.example.praktikumbackend.persistence.QuizRepository;
import org.example.praktikumbackend.persistence.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizQuestionService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizQuestionService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<QuizQuestion> getAllQuizzQuestions() {
        return quizRepository
                .findAll()
                .stream()
                .filter(Quiz::getIsActive)
                .map(quiz -> new QuizQuestion(quiz.getId(), quiz.getName()))
                .collect(Collectors.toList());
    }
}
