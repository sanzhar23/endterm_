package org.example.model;

import org.example.utils.Validatable;

public class Question implements Validatable {

    private int id;
    private int quizId;
    private String text;
    private String correctAnswer;
    private int points;

    public Question(int quizId, String text, String correctAnswer, int points) {
        this(0, quizId, text, correctAnswer, points);
    }

    public Question(int id, int quizId, String text, String correctAnswer, int points) {
        this.id = id;
        this.quizId = quizId;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    public int getId() { return id; }
    public int getQuizId() { return quizId; }
    public String getText() { return text; }
    public String getCorrectAnswer() { return correctAnswer; }
    public int getPoints() { return points; }

    @Override
    public void validate() {
        if (quizId <= 0) throw new IllegalArgumentException("quizId must be positive");
        if (text == null || text.isBlank()) throw new IllegalArgumentException("Question text cannot be empty");
        if (correctAnswer == null || correctAnswer.isBlank()) throw new IllegalArgumentException("Correct answer cannot be empty");
        if (points <= 0) throw new IllegalArgumentException("Points must be positive");
    }
}
