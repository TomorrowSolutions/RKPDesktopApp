package ru.kafpin.rkplab8.models;

import lombok.Data;

@Data
public class Service {
    private int id;
    private String name;
    private Double price;
    private int periodOfExecution;

    public Service(int id, String name, Double price, int periodOfExecution) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.periodOfExecution = periodOfExecution;
    }

    public Service(String name, Double price, int periodOfExecution) {
        this.name = name;
        this.price = price;
        this.periodOfExecution = periodOfExecution;
    }

    public Service() {
        this(0,"",0d,0);
    }
}
