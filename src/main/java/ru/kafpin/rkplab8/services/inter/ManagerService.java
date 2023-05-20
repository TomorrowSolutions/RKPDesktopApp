package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;

public interface ManagerService {
    Collection<Manager> findAll();
    Manager findOneById(int id);
    int create(Manager manager);
    int update(Manager manager);
    int delete(int id);
}
