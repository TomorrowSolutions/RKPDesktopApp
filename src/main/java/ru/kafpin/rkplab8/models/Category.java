package ru.kafpin.rkplab8.models;

import lombok.Data;

@Data
public class Category {
    private int id;
    private String name;
    private double salary;

    public Category(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Category(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Category() {
        this(0,"",0);
    }
}
