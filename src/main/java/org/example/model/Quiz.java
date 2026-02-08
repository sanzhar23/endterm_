package org.example.model;

public abstract class Quiz {
    protected int id;
    protected String name;
    protected String level;

    protected Quiz(int id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLevel() { return level; }

    public String label() {
        return "[" + level + "] " + name;
    }
}
