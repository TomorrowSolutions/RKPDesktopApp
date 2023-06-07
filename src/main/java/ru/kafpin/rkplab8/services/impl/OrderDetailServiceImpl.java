package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Order;
import ru.kafpin.rkplab8.models.OrderDetail;
import ru.kafpin.rkplab8.repositories.impl.ManagerRepositoryImpl;
import ru.kafpin.rkplab8.repositories.impl.OrderDetailRepositoryImpl;
import ru.kafpin.rkplab8.repositories.impl.OrderRepositoryImpl;
import ru.kafpin.rkplab8.services.inter.ManagerService;
import ru.kafpin.rkplab8.services.inter.OrderDetailService;
import ru.kafpin.rkplab8.services.inter.OrderService;

import java.util.Collection;
/**
 * Реализация {@link OrderDetailService}
 * */
public class OrderDetailServiceImpl implements OrderDetailService {
    /**
     * Объект {@link OrderDetailRepositoryImpl} для взаимодействия с репозиторием
     * */
    private final OrderDetailRepositoryImpl ordDetRep;
    /**
     * Конструктор для заполнения объекта {@link OrderDetailRepositoryImpl}
     * */
    public OrderDetailServiceImpl() {
        ordDetRep = new OrderDetailRepositoryImpl();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<OrderDetail> findAll() {
        return ordDetRep.findAll();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public OrderDetail findOneById(int id) {
        var finded = ordDetRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int create(OrderDetail orderDetail) {
        return ordDetRep.save(orderDetail);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int update(OrderDetail orderDetail) {
        return ordDetRep.save(orderDetail);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int delete(OrderDetail orderDetail) {
        return ordDetRep.delete(orderDetail);
    }
}
