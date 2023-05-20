package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.OrderDetail;

import java.util.Collection;

public interface OrderDetailService {
    Collection<OrderDetail> findAll();
    OrderDetail findOneById(int id);
    int create(OrderDetail orderDetail);
    int update(OrderDetail orderDetail);
    int delete(int id);
}
