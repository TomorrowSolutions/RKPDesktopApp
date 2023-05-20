package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;
import java.util.Optional;

public interface GuardedObjectRepository {
    Collection<Manager> findAll();
    Optional<Manager> findOneById(int id);
    int save(Manager manager);
    int delete(Manager manager);
}
