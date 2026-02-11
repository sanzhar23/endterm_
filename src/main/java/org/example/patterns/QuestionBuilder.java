package org.example.patterns;

import org.example.model.Question;

public class QuestionBuilder {

    private int quizId;
    private String text;
    private String correctAnswer;
    private int points;

    public QuestionBuilder quizId(int quizId) {
        this.quizId = quizId;
        return this;
    }

    public QuestionBuilder text(String text) {
        this.text = text;
        return this;
    }

    public QuestionBuilder correctAnswer(String answer) {
        this.correctAnswer = answer;
        return this;
    }

    public QuestionBuilder points(int points) {
        this.points = points;
        return this;
    }

    public Question build() {
        return new Question(quizId, text, correctAnswer, points);
    }
}
