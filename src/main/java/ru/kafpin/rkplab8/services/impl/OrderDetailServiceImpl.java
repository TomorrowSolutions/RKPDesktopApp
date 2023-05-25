package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Order;
import ru.kafpin.rkplab8.models.OrderDetail;
import ru.kafpin.rkplab8.repositories.impl.OrderDetailRepositoryImpl;
import ru.kafpin.rkplab8.repositories.impl.OrderRepositoryImpl;
import ru.kafpin.rkplab8.services.inter.OrderDetailService;
import ru.kafpin.rkplab8.services.inter.OrderService;

import java.util.Collection;

public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepositoryImpl ordDetRep;

    public OrderDetailServiceImpl() {
        ordDetRep = new OrderDetailRepositoryImpl();
    }

    @Override
    public Collection<OrderDetail> findAll() {
        return ordDetRep.findAll();
    }

    @Override
    public OrderDetail findOneById(int id) {
        var finded = ordDetRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }

    @Override
    public int create(OrderDetail orderDetail) {
        return ordDetRep.save(orderDetail);
    }

    @Override
    public int update(OrderDetail orderDetail) {
        return ordDetRep.save(orderDetail);
    }

    @Override
    public int delete(OrderDetail orderDetail) {
        return ordDetRep.delete(orderDetail);
    }
}
