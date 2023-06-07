package ru.kafpin.rkplab8.models;

import lombok.Data;

import java.util.stream.Stream;
/**
 *
 * Класс охраняемый объекта со свойствами <b>id</b>,<b>name</b>,<b>image</b>,<b>city</b>,<b>street</b>,<b>building</b>
 * <p>
 *     Данный класс позволяет описать охраняемый объект.
 * </p>
 * <p>
 *     Так же данный класс служит хранилищем для информации из базы данных (MVC)
 * </p>
 * @author Вадим Гуреев
 * @version 1.0
 */
@Data
public class GuardedObject {
    /**Поле id*/
    private int id;
    /**Поле название*/
    private String name;
    /**Поле, в котором хранится относительный путь к изображению*/
    private String image;
    /**Поле город*/
    private String city;
    /**Поле улица*/
    private String street;
    /**Поле здание*/
    private String building;
    /**Полный конструктор предназначен для выборки данных из базы, изменения или удаления записи в базе данных
     * @param id идентификатор
     * @param name название
     * @param image изображение
     * @param city город
     * @param street город
     * @param building город
     * @see GuardedObject#GuardedObject()
     * @see GuardedObject#GuardedObject(String, String, String, String, String)
     * **/
    public GuardedObject(int id, String name, String image, String city, String street, String building) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.city = city;
        this.street = street;
        this.building = building;
    }
    /**Конструктор без <b>id</b> предназначен для добавления новой записи в базу
     * @param name название
     * @param image изображение
     * @param city город
     * @param street город
     * @param building город
     * @see GuardedObject#GuardedObject(int, String, String, String, String, String)
     * @see GuardedObject#GuardedObject()
     * **/
    public GuardedObject(String name, String image, String city, String street, String building) {
        this.name = name;
        this.image = image;
        this.city = city;
        this.street = street;
        this.building = building;
    }
    /**Пустой конструктор имеет вспомогательную роль при добавлении новой записи в базу
     * @see GuardedObject#GuardedObject(int, String, String, String, String, String)
     * @see GuardedObject#GuardedObject(String, String, String, String, String)
     * */
    public GuardedObject() {
        this(0,null,null,null,null,null);
    }
}
