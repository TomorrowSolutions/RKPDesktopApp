package ru.kafpin.rkplab8.models;

import lombok.Data;

import java.util.stream.Stream;
@Data
public class GuardedObject {
    private int id;
    private byte[] image;
    private String city;
    private String street;
    private String building;

    public GuardedObject(int id, byte[] image, String city, String street, String building) {
        this.id = id;
        this.image = image;
        this.city = city;
        this.street = street;
        this.building = building;
    }

    public GuardedObject(byte[] image, String city, String street, String building) {
        this.image = image;
        this.city = city;
        this.street = street;
        this.building = building;
    }

    public GuardedObject() {
        this(0,null,"","","");
    }
}
