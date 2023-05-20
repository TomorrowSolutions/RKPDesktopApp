package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.models.Service;

import java.util.Collection;
import java.util.Optional;

public interface ServiceRepository {
    Collection<Service> findAll();
    Optional<Service> findOneById(int id);
    int save(Service service);
    int delete(Service service);
}
