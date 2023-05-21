package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.GuardedObject;

import java.util.Collection;
import java.util.Optional;

public interface GuardedObjectRepository {
    Collection<GuardedObject> findAll();
    Optional<GuardedObject> findOneById(int id);
    int save(GuardedObject gObject);
    int delete(GuardedObject gObject);
}
