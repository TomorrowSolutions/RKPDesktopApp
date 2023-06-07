package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.repositories.impl.ClientRepositoryImpl;
import ru.kafpin.rkplab8.repositories.impl.GuardedObjectRepositoryImpl;
import ru.kafpin.rkplab8.repositories.inter.ClientRepository;
import ru.kafpin.rkplab8.services.inter.ClientService;
import ru.kafpin.rkplab8.services.inter.GuardedObjectService;

import java.util.Collection;
/**
 * Реализация {@link GuardedObjectService}
 * */
public class GuardedObjectServiceImpl implements GuardedObjectService {
    /**
     * Объект {@link GuardedObjectRepositoryImpl} для взаимодействия с репозиторием
     * */
    private final GuardedObjectRepositoryImpl objRep;
    /**
     * Конструктор для заполнения объекта {@link GuardedObjectRepositoryImpl}
     * */
    public GuardedObjectServiceImpl() {
        objRep = new GuardedObjectRepositoryImpl();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<GuardedObject> findAll() {
        return objRep.findAll();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public GuardedObject findOneById(int id) {
        var finded = objRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int create(GuardedObject guardedObject) {
        return objRep.save(guardedObject);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int update(GuardedObject guardedObject) {
        return objRep.save(guardedObject);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int delete(GuardedObject guardedObject) {
       return  objRep.delete(guardedObject);
    }
}
