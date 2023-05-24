package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Service;
import ru.kafpin.rkplab8.repositories.impl.ServiceRepositoryImpl;
import ru.kafpin.rkplab8.services.inter.ServiceService;

import java.util.Collection;

public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepositoryImpl servRep;

    public ServiceServiceImpl() {
        servRep=new ServiceRepositoryImpl();
    }

    @Override
    public Collection<Service> findAll() {
        return servRep.findAll();
    }

    @Override
    public Service findOneById(int id) {
        var finded = servRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }

    @Override
    public int create(Service service) {
        return servRep.save(service);
    }

    @Override
    public int update(Service service) {
        return servRep.save(service);
    }

    @Override
    public int delete(int id) {
        var finded = servRep.findOneById(id);
        if(finded.isEmpty())
            return 0;
        return servRep.delete(finded.stream().findFirst().orElse(null));
    }
}
