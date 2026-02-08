package org.example.controller;

import org.example.dto.QuizRequestDto;
import org.example.model.Quiz;
import org.example.repository.QuizRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizRepository quizRepository = new QuizRepository();

    @GetMapping
    public List<Quiz> getAll() {
        return quizRepository.getAll();
    }

    @GetMapping("/{id}")
    public Quiz getById(@PathVariable int id) {
        Quiz quiz = quizRepository.getById(id);
        if (quiz == null) {
            throw new RuntimeException("Quiz not found: " + id);
        }
        return quiz;
    }

    @PostMapping
    public Quiz create(@RequestBody QuizRequestDto dto) {
        if (dto.name == null || dto.name.isBlank()) {
            throw new RuntimeException("name is required");
        }
        if (dto.level == null || (!dto.level.equals("EASY") && !dto.level.equals("HARD"))) {
            throw new RuntimeException("level must be EASY or HARD");
        }
        return quizRepository.create(dto.name, dto.level);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        quizRepository.delete(id);
    }
}
