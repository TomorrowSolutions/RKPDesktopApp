package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.repositories.impl.GuardedObjectRepositoryImpl;
import ru.kafpin.rkplab8.repositories.inter.ClientRepository;
import ru.kafpin.rkplab8.services.inter.GuardedObjectService;

import java.util.Collection;

public class GuardedObjectServiceImpl implements GuardedObjectService {
    private final GuardedObjectRepositoryImpl objRep;

    public GuardedObjectServiceImpl() {
        objRep = new GuardedObjectRepositoryImpl();
    }

    @Override
    public Collection<GuardedObject> findAll() {
        return objRep.findAll();
    }

    @Override
    public GuardedObject findOneById(int id) {
        var finded = objRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }

    @Override
    public int create(GuardedObject guardedObject) {
        return objRep.save(guardedObject);
    }

    @Override
    public int update(GuardedObject guardedObject) {
        return objRep.save(guardedObject);
    }

    @Override
    public int delete(GuardedObject guardedObject) {
       return  objRep.delete(guardedObject);
    }
}
