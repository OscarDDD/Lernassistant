package org.example.praktikumbackend.controller;

import org.example.praktikumbackend.persistence.QuizQuestion;
import org.example.praktikumbackend.service.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz_questions")
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;

    @Autowired
    public QuizQuestionController(QuizQuestionService quizQuestionService) {
        this.quizQuestionService = quizQuestionService;
    }

    @GetMapping
    public ResponseEntity<List<QuizQuestion>> getAllQuizQuestions() {
        return ResponseEntity.ok(quizQuestionService.getAllQuizzQuestions());
    }
}
