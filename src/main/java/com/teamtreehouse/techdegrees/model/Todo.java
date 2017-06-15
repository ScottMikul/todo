package com.teamtreehouse.techdegrees.model;

/**
 * Created by scott on 6/14/2017.
 */
public class Todo {
    private int id;
    private String name;
    private boolean completed;
    private boolean edited;

    public Todo(String name, boolean completed, boolean edited) {
        this.name = name;
        this.completed = completed;
        this.edited = edited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", completed=" + completed +
                ", edited=" + edited +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (id != todo.id) return false;
        if (completed != todo.completed) return false;
        if (edited != todo.edited) return false;
        return name.equals(todo.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (completed ? 1 : 0);
        result = 31 * result + (edited ? 1 : 0);
        return result;
    }
}
