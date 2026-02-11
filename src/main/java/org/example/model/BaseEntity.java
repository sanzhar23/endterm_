package org.example.model;

public abstract class BaseEntity {
    protected int id;
    protected String name;

    protected BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}
