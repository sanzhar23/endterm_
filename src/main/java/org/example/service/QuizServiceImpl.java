package org.example.service;

import org.example.exception.DuplicateResourceException;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Quiz;
import org.example.patterns.QuizFactory;
import org.example.repository.QuizRepository;
import org.example.service.interfaces.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository repo;

    public QuizServiceImpl(QuizRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Quiz> getAll() {
        return repo.getAll();
    }

    @Override
    public Quiz getById(int id) {
        Quiz q = repo.getById(id);
        if (q == null) throw new ResourceNotFoundException("Quiz not found: " + id);
        return q;
    }

    @Override
    public Quiz create(String name, String level) {
        Quiz q = QuizFactory.create(name, level);
        q.validate();
        return repo.create(q.getName(), q.getLevel());
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }
}
