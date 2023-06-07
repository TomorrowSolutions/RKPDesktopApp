package ru.kafpin.rkplab8.models;

import lombok.Data;
/**
 *
 * Класс подробности заказа со свойствами <b>id</b>,<b>name</b>,<b>price</b>,<b>periodOfExecution</b>
 * <p>
 *     Данный класс позволяет описать  услугу.
 * </p>
 * <p>
 *     Так же данный класс служит хранилищем для информации из базы данных (MVC)
 * </p>
 * @author Вадим Гуреев
 * @version 1.0
 */
@Data
public class Service {
    /**Поле идентификатора*/
    private int id;
    /**Поле название*/
    private String name;
    /**Поле стоимость*/
    private Double price;
    /**Поле время выполнения*/
    private int periodOfExecution;
    /**Полный конструктор предназначен для выборки данных из базы, изменения или удаления записи в базе данных
     * @param id идентификатор
     * @param name название
     * @param price стоимость
     * @param periodOfExecution время выполнения
     * @see Service#Service(String, Double, int)
     * @see Service#Service()
     * **/

    public Service(int id, String name, Double price, int periodOfExecution) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.periodOfExecution = periodOfExecution;
    }
    /**Конструктор без <b>id</b> предназначен для добавления новой записи в базу
     * @param name название
     * @param price стоимость
     * @param periodOfExecution время выполнения
     * @see Service#Service(int,String, Double, int)
     * @see Service#Service()
     * **/
    public Service(String name, Double price, int periodOfExecution) {
        this.name = name;
        this.price = price;
        this.periodOfExecution = periodOfExecution;
    }
    /**Пустой конструктор имеет вспомогательную роль при добавлении новой записи в базу
     * @see Service#Service(int,String, Double, int)
     * @see Service#Service(String, Double, int)
     * **/
    public Service() {
        this(0,"",0d,0);
    }
}
