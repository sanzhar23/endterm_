package org.example.model;

import org.example.utils.Scorable;
import org.example.utils.Validatable;

public abstract class Quiz extends BaseEntity implements Scorable, Validatable {

    protected String level;

    protected Quiz(int id, String name, String level) {
        super(id, name);
        this.level = level;
    }

    public String getLevel() { return level; }

    public abstract int difficultyWeight();

    public String label() {
        return "[" + level + "] " + name;
    }

    @Override
    public void validate() {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Quiz name cannot be empty");
        }
        if (!"EASY".equals(level) && !"HARD".equals(level)) {
            throw new IllegalArgumentException("Invalid quiz level: " + level);
        }
    }

    @Override
    public int maxScore() {
        return difficultyWeight();
    }
}
