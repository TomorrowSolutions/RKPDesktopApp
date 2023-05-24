package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Order;
import ru.kafpin.rkplab8.repositories.impl.OrderRepositoryImpl;
import ru.kafpin.rkplab8.services.inter.OrderService;

import java.util.Collection;

public class OrderServiceImpl implements OrderService {
    private final OrderRepositoryImpl ordRep;

    public OrderServiceImpl() {
        ordRep= new OrderRepositoryImpl();
    }

    @Override
    public Collection<Order> findAll() {
        return ordRep.findAll();
    }

    @Override
    public Order findOneById(int id) {
        var finded = ordRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }

    @Override
    public int create(Order order) {
        return ordRep.save(order);
    }

    @Override
    public int update(Order order) {
        return ordRep.save(order);
    }

    @Override
    public int delete(int id) {
        var finded = ordRep.findOneById(id);
        if(finded.isEmpty())
            return 0;
        return ordRep.delete(finded.stream().findFirst().orElse(null));
    }
}
