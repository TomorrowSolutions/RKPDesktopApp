package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.OrderDetail;
import ru.kafpin.rkplab8.models.Service;

import java.util.Collection;
import java.util.Optional;

public interface OrderDetailRepository {
    Collection<OrderDetail> findAll();
    Optional<OrderDetail> findOneById(int id);
    int save(OrderDetail orderDetail);
    int delete(OrderDetail orderDetail);
}
