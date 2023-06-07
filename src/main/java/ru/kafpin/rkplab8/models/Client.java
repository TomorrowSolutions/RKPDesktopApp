package ru.kafpin.rkplab8.models;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
/**
 *
 * Класс клиента со свойствами <b>id</b>,<b>surname</b>,<b>name</b>,<b>patronymic</b>,<b>phoneNum</b>,<b>typeOfPerson</b>,
 * <b>city</b>,<b>street</b>,<b>building</b>,<b>accountnumber</b>
 * <p>
 *     Данный класс позволяет описать клиента.
 * </p>
 * <p>
 *     Так же данный класс служит хранилищем для информации из базы данных (MVC)
 * </p>
 * @author Вадим Гуреев
 * @version 1.0
 */
@Data
public class Client {
    /**Поле id*/
    private int id;
    /**Поле фамилия*/
    private String surname;
    /**Поле имя*/
    private String name;
    /**Поле отчество*/
    private String patronymic;
    /**Поле номер телефона*/
    private String phoneNum;
    /**Поле тип лица, представленное перечислением {@link TypeOfPerson}**/
    private TypeOfPerson typeOfPerson;
    /**Поле город*/
    private String city;
    /**Поле улица*/
    private String street;
    /**Поле здание*/
    private String building;
    /**Поле номер счета*/
    private String accountnumber;
    /**Полный конструктор предназначен для выборки данных из базы, изменения или удаления записи в базе данных
     * @param id идентификатор
     * @param surname фамилия
     * @param name имя
     * @param patronymic отчество
     * @param phoneNum номер телефона
     * @param typeOfPerson тип лица
     * @param city город
     * @param street улица
     * @param building здание
     * @param accountnumber номер счета
     * @see Client#Client(String, String, String, String, TypeOfPerson, String, String, String, String)
     * @see Client#Client()
     * */
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
    /**Конструктор без <b>id</b> предназначен для добавления новой записи в базу
     * @param surname фамилия
     * @param name имя
     * @param patronymic отчество
     * @param phoneNum номер телефона
     * @param typeOfPerson тип лица
     * @param city город
     * @param street улица
     * @param building здание
     * @param accountnumber номер счета
     * @see Client#Client(int, String, String, String, String, TypeOfPerson, String, String, String, String)
     * @see Client#Client()
     * */
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
    /**Пустой конструктор имеет вспомогательную роль при добавлении новой записи в базу
     * @see Client#Client(int, String, String, String, String, TypeOfPerson, String, String, String, String)
     * @see Client#Client(String, String, String, String, TypeOfPerson, String, String, String, String)
     * */
    public Client() {
        this(0,"","","","",null,"","","","");
    }
    /**Функция для получения ФИО
     * @return Возвращает строку из {@link Client#surname},{@link Client#name},{@link Client#patronymic} через пробел
     * **/
    public String getFIO(){
        return String.join(" ",surname,name,patronymic);
    }
}
