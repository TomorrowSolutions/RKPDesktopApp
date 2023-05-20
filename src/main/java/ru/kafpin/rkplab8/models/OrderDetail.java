package ru.kafpin.rkplab8.models;

import lombok.Data;

@Data
public class OrderDetail {
    private int id;
    private Order order;
    private Object object;
    private Service service;
    private int num;

    public OrderDetail(int id, Order order, Object object, Service service, int num) {
        this.id = id;
        this.order = order;
        this.object = object;
        this.service = service;
        this.num = num;
    }

    public OrderDetail(Order order, Object object, Service service, int num) {
        this.order = order;
        this.object = object;
        this.service = service;
        this.num = num;
    }

    public OrderDetail() {
        this(0,null,null,null,0);
    }
}
