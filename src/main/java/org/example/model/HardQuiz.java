package org.example.model;

public class HardQuiz extends Quiz {

    public HardQuiz(int id, String name) {
        super(id, name, "HARD");
    }

    public HardQuiz(String name) {
        this(0, name);
    }

    @Override
    public int difficultyWeight() {
        return 20;
    }
}
