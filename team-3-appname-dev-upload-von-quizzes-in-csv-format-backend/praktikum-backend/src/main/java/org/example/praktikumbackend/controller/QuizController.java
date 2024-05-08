package org.example.praktikumbackend.controller;

import org.example.praktikumbackend.persistence.Quiz;
import org.example.praktikumbackend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.createQuiz(quiz));
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable UUID quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{quizId}/isActive")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable UUID quizId, @RequestBody boolean quizDetails) {
        Quiz updatedQuiz = quizService.updateQuiz(quizId, quizDetails);
        return ResponseEntity.ok(updatedQuiz);
    }

}
