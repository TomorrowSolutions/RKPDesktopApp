package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Order;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository {
    Collection<Order> findAll();
    Optional<Order> findOneById(int id);
    int save(Order order);
    int delete(Order order);
}
