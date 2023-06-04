package ru.kafpin.rkplab8.models;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class Manager {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String education;
    private Category category;
    private int categoryId;
    private LocalDate dateOfStart;
    private String accountNumber;

    public Manager(int id, String surname, String name, String patronymic, String education, Category category, int categoryId, LocalDate dateOfStart, String accountnumber) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.education = education;
        this.category = category;
        this.categoryId = categoryId;
        this.dateOfStart = dateOfStart;
        this.accountNumber = accountnumber;
    }
    public Manager(String surname, String name, String patronymic, String education, Category category, int categoryId, LocalDate dateOfStart, String accountnumber) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.education = education;
        this.category = category;
        this.categoryId = categoryId;
        this.dateOfStart = dateOfStart;
        this.accountNumber = accountnumber;
    }

    public Manager() {
        this(0,null,null,null,null,null,0,null,null);
    }

    public String getFIO(){
       return String.join(" ",surname,name,patronymic);
    }
}
