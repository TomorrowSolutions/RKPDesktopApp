package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.repositories.impl.ManagerRepositoryImpl;
import ru.kafpin.rkplab8.services.inter.ManagerService;

import java.util.Collection;

public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepositoryImpl manRep;

    public ManagerServiceImpl() {
        manRep=new ManagerRepositoryImpl();
    }

    @Override
    public Collection<Manager> findAll() {
        return manRep.findAll();
    }

    @Override
    public Manager findOneById(int id) {
        var finded = manRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }

    @Override
    public int create(Manager manager) {
        return manRep.save(manager);
    }

    @Override
    public int update(Manager manager) {
        return manRep.save(manager);
    }

    @Override
    public int delete(Manager manager) {
        return manRep.delete(manager);
    }
}
