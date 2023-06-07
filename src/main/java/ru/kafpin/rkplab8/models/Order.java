package ru.kafpin.rkplab8.models;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *
 * Класс заказа со свойствами <b>id</b>,<b>client</b>,<b>clientid</b>,<b>manager</b>,<b>managerId</b>,<b>dateOfSigning</b>,
 * <b>dateOfComplete</b>,<b>price</b>
 * <p>
 *     Данный класс позволяет описать заказ.
 * </p>
 * <p>
 *     Так же данный класс служит хранилищем для информации из базы данных (MVC)
 * </p>
 * @author Вадим Гуреев
 * @version 1.0
 */
@Data
public class Order {
    /**Поле id*/
    private int id;
    /**Поле клиент, представленное классом {@link Client}*/
    private Client client;
    /**Поле идентификатор клиента*/
    private int clientId;
    /**Поле менеджер , представленное классом {@link Manager}*/
    private Manager manager;
    /**Поле идентификатор менеджера*/
    private int managerId;
    /**Поле дата подписания*/
    private LocalDate dateOfSigning;
    /**Поле дата выполнения*/
    private LocalDate dateOfComplete;
    /**Поле цена*/
    private double price;
    /**Полный конструктор предназначен для выборки данных из базы, изменения или удаления записи в базе данных
     * @param id идентификатор
     * @param client клиент
     * @param clientId идентификатор клиента
     * @param manager менеджер
     * @param managerId идентификатор менеджера
     * @param dateOfSigning дата подписания
     * @param dateOfComplete дата выполнения
     * @param price цена
     * @see Order#Order(Client, int, Manager, int, LocalDate, LocalDate, double)
     * @see Order#Order()
     * **/
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
    /**Конструктор без <b>id</b> предназначен для добавления новой записи в базу
     * @param client клиент
     * @param clientId идентификатор клиента
     * @param manager менеджер
     * @param managerId идентификатор менеджера
     * @param dateOfSigning дата подписания
     * @param dateOfComplete дата выполнения
     * @param price цена
     * @see Order#Order(int,Client, int, Manager, int, LocalDate, LocalDate, double)
     * @see Order#Order()
     * **/
    public Order(Client client, int clientId, Manager manager, int managerId, LocalDate dateOfSigning, LocalDate dateOfComplete, double price) {
        this.client = client;
        this.clientId = clientId;
        this.manager = manager;
        this.managerId = managerId;
        this.dateOfSigning = dateOfSigning;
        this.dateOfComplete = dateOfComplete;
        this.price = price;
    }
    /**Пустой конструктор имеет вспомогательную роль при добавлении новой записи в базу
     * @see Order#Order(int,Client, int, Manager, int, LocalDate, LocalDate, double)
     * @see Order#Order(Client, int, Manager, int, LocalDate, LocalDate, double)
     * **/
    public Order() {
        this(0,null,0,null,0,null,null,0);
    }
}
