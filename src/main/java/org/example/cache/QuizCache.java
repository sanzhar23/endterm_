package org.example.cache;

import org.example.model.Quiz;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuizCache {
    private static QuizCache instance;

    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    private QuizCache() {
    }

    public static QuizCache getInstance() {
        if (instance == null) {
            instance = new QuizCache();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public List<Quiz> getAllQuizzes() {
        return (List<Quiz>) cache.get("allQuizzes");
    }

    public void putAllQuizzes(List<Quiz> quizzes) {
        cache.put("allQuizzes", quizzes);
    }

    public void clear() {
        cache.clear();
    }
}
