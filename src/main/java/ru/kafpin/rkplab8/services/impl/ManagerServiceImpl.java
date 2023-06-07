package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.repositories.impl.GuardedObjectRepositoryImpl;
import ru.kafpin.rkplab8.repositories.impl.ManagerRepositoryImpl;
import ru.kafpin.rkplab8.services.inter.GuardedObjectService;
import ru.kafpin.rkplab8.services.inter.ManagerService;

import java.util.Collection;
/**
 * Реализация {@link ManagerService}
 * */
public class ManagerServiceImpl implements ManagerService {
    /**
     * Объект {@link ManagerRepositoryImpl} для взаимодействия с репозиторием
     * */
    private final ManagerRepositoryImpl manRep;
    /**
     * Конструктор для заполнения объекта {@link ManagerRepositoryImpl}
     * */
    public ManagerServiceImpl() {
        manRep=new ManagerRepositoryImpl();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<Manager> findAll() {
        return manRep.findAll();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Manager findOneById(int id) {
        var finded = manRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int create(Manager manager) {
        return manRep.save(manager);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int update(Manager manager) {
        return manRep.save(manager);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int delete(Manager manager) {
        return manRep.delete(manager);
    }
}
