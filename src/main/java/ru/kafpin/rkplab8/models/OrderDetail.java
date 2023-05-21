package ru.kafpin.rkplab8.models;

import lombok.Data;

@Data
public class OrderDetail {
    private int id;
    private Order order;
    private int orderId;
    private GuardedObject guardedObject;
    private int gObjectId;
    private Service service;
    private int serviceId;
    private int quantity;

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

    public OrderDetail(Order order, int orderId, GuardedObject guardedObject, int gObjectId, Service service, int serviceId, int quantity) {
        this.order = order;
        this.orderId = orderId;
        this.guardedObject = guardedObject;
        this.gObjectId = gObjectId;
        this.service = service;
        this.serviceId = serviceId;
        this.quantity = quantity;
    }

    public OrderDetail() {
        this(0,null,0,null,0,null,0,0);
    }
}
