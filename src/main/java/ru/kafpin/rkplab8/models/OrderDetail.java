package ru.kafpin.rkplab8.models;

import lombok.Data;

import java.time.LocalDate;

/**
 *
 * Класс подробности заказа со свойствами <b>id</b>,<b>order</b>,<b>orderId</b>,<b>guardedObject</b>,<b>gObjectId</b>,<b>service</b>,<b>serviceId</b>,<b>quantity</b>
 * <p>
 *     Данный класс позволяет описать  подробность заказа.
 * </p>
 * <p>
 *     Так же данный класс служит хранилищем для информации из базы данных (MVC)
 * </p>
 * @author Вадим Гуреев
 * @version 1.0
 */
@Data
public class OrderDetail {
    /**Поле идентификатора*/
    private int id;
    /**Поле заказ, представленное классом {@link Order} */
    private Order order;
    /**Поле идентификатор заказа*/
    private int orderId;
    /**Поле объект, представленное классом {@link GuardedObject} */
    private GuardedObject guardedObject;
    /**Поле идентификатор объекта*/
    private int gObjectId;
    /**Поле услуга, представленное классом {@link Service} */
    private Service service;
    /**Поле идентификатор услуги*/
    private int serviceId;
    /**Поле количество*/
    private int quantity;
    /**Полный конструктор предназначен для выборки данных из базы, изменения или удаления записи в базе данных
     * @param id идентификатор
     * @param order заказ
     * @param orderId идентификатор заказа
     * @param guardedObject объект
     * @param gObjectId идентификатор объекта
     * @param service услуга
     * @param serviceId идентификатор услуги
     * @param quantity количество
     * @see OrderDetail#OrderDetail(Order, int, GuardedObject, int, Service, int, int)
     * @see OrderDetail#OrderDetail()
     * **/
    public OrderDetail(int id, Order order, int orderId, GuardedObject guardedObject, int gObjectId, Service service, int serviceId, int quantity) {
        this.id = id;
        this.order = order;
        this.orderId = orderId;
        this.guardedObject = guardedObject;
        this.gObjectId = gObjectId;
        this.service = service;
        this.serviceId = serviceId;
        this.quantity = quantity;
    }
    /**Конструктор без <b>id</b> предназначен для добавления новой записи в базу
     * @param order заказ
     * @param orderId идентификатор заказа
     * @param guardedObject объект
     * @param gObjectId идентификатор объекта
     * @param service услуга
     * @param serviceId идентификатор услуги
     * @param quantity количество
     * @see OrderDetail#OrderDetail(int,Order, int, GuardedObject, int, Service, int, int)
     * @see OrderDetail#OrderDetail()
     * **/
    public OrderDetail(Order order, int orderId, GuardedObject guardedObject, int gObjectId, Service service, int serviceId, int quantity) {
        this.order = order;
        this.orderId = orderId;
        this.guardedObject = guardedObject;
        this.gObjectId = gObjectId;
        this.service = service;
        this.serviceId = serviceId;
        this.quantity = quantity;
    }
    /**Пустой конструктор имеет вспомогательную роль при добавлении новой записи в базу
     * @see OrderDetail#OrderDetail(int,Order, int, GuardedObject, int, Service, int, int)
     * @see OrderDetail#OrderDetail(Order, int, GuardedObject, int, Service, int, int)
     * **/
    public OrderDetail() {
        this(0,null,0,null,0,null,0,0);
    }
}
