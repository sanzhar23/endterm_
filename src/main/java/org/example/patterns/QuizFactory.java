package org.example.patterns;

import org.example.model.EasyQuiz;
import org.example.model.HardQuiz;
import org.example.model.Quiz;

public class QuizFactory {

    public static Quiz create(String name, String level) {
        if ("EASY".equals(level)) return new EasyQuiz(name);
        if ("HARD".equals(level)) return new HardQuiz(name);
        throw new IllegalArgumentException("Unknown level: " + level);
    }
}
