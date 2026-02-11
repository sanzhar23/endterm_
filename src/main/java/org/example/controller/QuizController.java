package org.example.controller;

import org.example.dto.QuizRequestDto;
import org.example.model.Quiz;
import org.example.service.interfaces.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping
    public List<Quiz> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Quiz getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public Quiz create(@RequestBody QuizRequestDto dto) {
        return service.create(dto.name, dto.level);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
