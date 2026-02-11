package org.example.service;

import org.example.exception.DuplicateResourceException;
import org.example.model.Question;
import org.example.repository.QuestionRepository;
import org.example.service.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repo;

    public QuestionServiceImpl(QuestionRepository repo) {
        this.repo = repo;
    }

    @Override
    public Question create(Question q) {
        q.validate();

        if (repo.existsSameQuestion(q.getQuizId(), q.getText(), q.getCorrectAnswer())) {
            throw new DuplicateResourceException("Duplicate question for quizId=" + q.getQuizId());
        }

        return repo.create(q);
    }

    @Override
    public List<Question> getByQuizId(int quizId) {
        return repo.getByQuizId(quizId);
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }
}
