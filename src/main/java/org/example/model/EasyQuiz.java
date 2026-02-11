package org.example.model;

public class EasyQuiz extends Quiz {

    public EasyQuiz(int id, String name) {
        super(id, name, "EASY");
    }

    public EasyQuiz(String name) {
        this(0, name);
    }

    @Override
    public int difficultyWeight() {
        return 10;
    }
}
