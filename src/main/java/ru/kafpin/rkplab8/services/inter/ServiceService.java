package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.Service;

import java.util.Collection;

public interface ServiceService {
    Collection<Service> findAll();
    Service findOneById(int id);
    int create(Service service);
    int update(Service service);
    int delete(int id);
}
