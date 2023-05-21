package ru.kafpin.rkplab8.models;

import lombok.Data;

import java.util.stream.Stream;
@Data
public class GuardedObject {
    private int id;
    private String name;
    private String image;
    private String city;
    private String street;
    private String building;

    public GuardedObject(int id, String name, String image, String city, String street, String building) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.city = city;
        this.street = street;
        this.building = building;
    }

    public GuardedObject(String name, String image, String city, String street, String building) {
        this.name = name;
        this.image = image;
        this.city = city;
        this.street = street;
        this.building = building;
    }

    public GuardedObject() {
        this(0,null,null,null,null,null);
    }
}
