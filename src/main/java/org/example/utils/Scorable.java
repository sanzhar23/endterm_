package org.example.utils;

public interface Scorable {
    int maxScore();

    default boolean isPassingScore(int score) {
        return score >= (maxScore() / 2);
    }

    static int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }
}
