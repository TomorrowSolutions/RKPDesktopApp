package ru.kafpin.rkplab8.models;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class Order {
    private int id;
    private Client client;
    private int clientId;
    private Manager manager;
    private int managerId;
    private LocalDate dateOfSigning;
    private LocalDate dateOfComplete;
    private double price;

    public Order(int id, Client client, int clientId, Manager manager, int managerId, LocalDate dateOfSigning, LocalDate dateOfComplete, double price) {
        this.id = id;
        this.client = client;
        this.clientId = clientId;
        this.manager = manager;
        this.managerId = managerId;
        this.dateOfSigning = dateOfSigning;
        this.dateOfComplete = dateOfComplete;
        this.price = price;
    }

    public Order(Client client, int clientId, Manager manager, int managerId, LocalDate dateOfSigning, LocalDate dateOfComplete, double price) {
        this.client = client;
        this.clientId = clientId;
        this.manager = manager;
        this.managerId = managerId;
        this.dateOfSigning = dateOfSigning;
        this.dateOfComplete = dateOfComplete;
        this.price = price;
    }

    public Order() {
        this(0,null,0,null,0,null,null,0);
    }
}
