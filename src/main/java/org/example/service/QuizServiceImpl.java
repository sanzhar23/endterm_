package org.example.service;

import org.example.exception.ResourceNotFoundException;
import org.example.model.Quiz;
import org.example.cache.QuizCache;
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

        QuizCache cache = QuizCache.getInstance();
        List<Quiz> cached = cache.getAllQuizzes();
        if (cached != null) {
            System.out.println("Loaded from cache");
            return cached;
        }
        System.out.println("Loaded from database");
        List<Quiz> quizzes = repo.getAll();
        cache.putAllQuizzes(quizzes);
        return quizzes;
    }


    @Override
    public Quiz getById(int id) {
        Quiz q = repo.getById(id);
        if (q == null) throw new ResourceNotFoundException("Quiz not found: " + id);
        return q;
    }

    @Override
    public Quiz create(String name, String level) {
        Quiz created = repo.create(name, level);
        QuizCache.getInstance().clear();
        return created;
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
        QuizCache.getInstance().clear();
    }
}
