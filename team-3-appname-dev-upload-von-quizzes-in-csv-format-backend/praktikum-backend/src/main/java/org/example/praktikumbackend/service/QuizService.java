package org.example.praktikumbackend.service;

import org.example.praktikumbackend.persistence.Quiz;
import org.example.praktikumbackend.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.UUID;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public void deleteQuiz(UUID quizId) {
        quizRepository.deleteById(quizId);
    }

    public Quiz updateQuiz(UUID quizId, boolean isActive) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found"));
        // Update necessary fields -> write failsafe, the quizDetails may contain anything or not the isActive property,
        //  it won't fail for this. This endpoint is theoretically extensible to also update also other properties.
        quiz.setIsActive(isActive);
        return quizRepository.save(quiz);
    }
}
