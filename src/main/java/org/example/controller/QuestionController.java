package org.example.controller;

import org.example.dto.QuestionRequestDto;
import org.example.model.Question;
import org.example.patterns.QuestionBuilder;
import org.example.service.interfaces.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes/{quizId}/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    //получение
    @GetMapping
    public List<Question> getByQuizId(@PathVariable int quizId) {
        return service.getByQuizId(quizId);
    }

    //создание вопроса
    @PostMapping
    public Question create(@PathVariable int quizId, @RequestBody QuestionRequestDto dto) {
        Question q = new QuestionBuilder()
                .quizId(quizId)
                .text(dto.text)
                .correctAnswer(dto.correctAnswer)
                .points(dto.points)
                .build();

        return service.create(q);
    }

    //удаление вопрос по айди
    @DeleteMapping("/{questionId}")
    public void delete(@PathVariable int quizId, @PathVariable int questionId) {
        service.delete(questionId);
    }
}
