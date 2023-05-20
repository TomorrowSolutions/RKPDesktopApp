package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;

public interface GuardedObjectService {
    Collection<GuardedObject> findAll();
    GuardedObject findOneById(int id);
    int create(GuardedObject guardedObject);
    int update(GuardedObject guardedObject);
    int delete(int id);
}
