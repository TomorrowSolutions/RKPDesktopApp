package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Order;

import java.util.Collection;

public interface OrderService {
    Collection<Order> findAll();
    Order findOneById(int id);
    int create(Order order);
    int update(Order order);
    int delete(Order order);}
