package ru.kafpin.rkplab8.models;

import lombok.Data;

/**
 * Класс категории со свойствами <b>id</b>,<b>name</b>,<b>salary</b>
 * <p>
 *     Данный класс позволяет описать категорию менеджера, которая будет определять его оклад.
 * </p>
 * <p>
 *     Так же данный класс служит хранилищем для информации из базы данных (MVC)
 * </p>
 * @author Вадим Гуреев
 * @version 1.0
 */
@Data
public class Category {
    /**Поле id*/
    private int id;
    /**Поле название*/
    private String name;
    /**Поле оклад*/
    private double salary;
    /**Полный конструктор предназначен для выборки данных из базы, изменения или удаления записи в базе данных
     * @param id идентификатор
     * @param name название
     * @param salary оклад
     * @see Category#Category(String, double)
     * @see Category#Category()
     * */
    public Category(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    /**Конструктор без <b>id</b> предназначен для добавления новой записи в базу
     * @param name название
     * @param salary оклад
     * @see Category#Category(int, String, double)
     * @see Category#Category()
     * */
    public Category(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    /**Пустой конструктор имеет вспомогательную роль при добавлении новой записи в базу
     * @see Category#Category(String, double)
     * @see Category#Category(int, String, double)
     * */
    public Category() {
        this(0,"",0);
    }
}
