package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Order;
import ru.kafpin.rkplab8.repositories.impl.OrderDetailRepositoryImpl;
import ru.kafpin.rkplab8.repositories.impl.OrderRepositoryImpl;
import ru.kafpin.rkplab8.services.inter.OrderDetailService;
import ru.kafpin.rkplab8.services.inter.OrderService;

import java.util.Collection;
/**
 * Реализация {@link OrderService}
 * */
public class OrderServiceImpl implements OrderService {
    /**
     * Объект {@link OrderRepositoryImpl} для взаимодействия с репозиторием
     * */
    private final OrderRepositoryImpl ordRep;
    /**
     * Конструктор для заполнения объекта {@link OrderRepositoryImpl}
     * */
    public OrderServiceImpl() {
        ordRep= new OrderRepositoryImpl();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<Order> findAll() {
        return ordRep.findAll();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Order findOneById(int id) {
        var finded = ordRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int create(Order order) {
        return ordRep.save(order);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int update(Order order) {
        return ordRep.save(order);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int delete(Order order) {
        return ordRep.delete(order);

    }
}
