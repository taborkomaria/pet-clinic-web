package ru.petclinic.servlets.models;

public abstract class Base {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
