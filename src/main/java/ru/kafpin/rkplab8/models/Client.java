package ru.kafpin.rkplab8.models;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class Client {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNum;
    private TypeOfPerson typeOfPerson;
    private String city;
    private String street;
    private String building;
    private String accountnumber;

    public Client(int id, String surname, String name, String patronymic, String phoneNum, TypeOfPerson typeOfPerson, String city, String street, String building, String accountnumber) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNum = phoneNum;
        this.typeOfPerson = typeOfPerson;
        this.city = city;
        this.street = street;
        this.building = building;
        this.accountnumber = accountnumber;
    }

    public Client(String surname, String name, String patronymic, String phoneNum, TypeOfPerson typeOfPerson, String city, String street, String building, String accountnumber) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNum = phoneNum;
        this.typeOfPerson = typeOfPerson;
        this.city = city;
        this.street = street;
        this.building = building;
        this.accountnumber = accountnumber;
    }
    public Client(String surname, String name, String patronymic, String phoneNum, TypeOfPerson typeOfPerson,
                  String city, String street, String building, String accountnumber,Boolean isTest) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNum = phoneNum;
        this.typeOfPerson = typeOfPerson;
        this.city = city;
        this.street = street;
        this.building = building;
        this.accountnumber = accountnumber;
    }

    public Client() {
        this(0,"","","","",null,"","","","");
    }

    public String getFIO(){
        return String.join(" ",surname,name,patronymic);
    }
}
