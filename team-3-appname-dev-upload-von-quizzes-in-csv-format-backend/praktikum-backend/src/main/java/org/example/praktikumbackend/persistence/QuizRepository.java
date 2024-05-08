package org.example.praktikumbackend.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> { }
