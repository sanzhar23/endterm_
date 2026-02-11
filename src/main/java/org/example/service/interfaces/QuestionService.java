package org.example.service.interfaces;

import org.example.model.Question;

import java.util.List;

public interface QuestionService {
    Question create(Question q);
    List<Question> getByQuizId(int quizId);
    void delete(int id);
}
