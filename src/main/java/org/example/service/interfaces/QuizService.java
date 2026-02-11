package org.example.service.interfaces;

import org.example.model.Quiz;

import java.util.List;

public interface QuizService {
    List<Quiz> getAll();
    Quiz getById(int id);
    Quiz create(String name, String level);
    void delete(int id);
}
