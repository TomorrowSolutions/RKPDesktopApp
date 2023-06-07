package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Service;
import ru.kafpin.rkplab8.repositories.impl.OrderRepositoryImpl;
import ru.kafpin.rkplab8.repositories.impl.ServiceRepositoryImpl;
import ru.kafpin.rkplab8.services.inter.OrderDetailService;
import ru.kafpin.rkplab8.services.inter.ServiceService;

import java.util.Collection;
/**
 * Реализация {@link ServiceService}
 * */
public class ServiceServiceImpl implements ServiceService {
    /**
     * Объект {@link ServiceRepositoryImpl} для взаимодействия с репозиторием
     * */
    private final ServiceRepositoryImpl servRep;
    /**
     * Конструктор для заполнения объекта {@link ServiceRepositoryImpl}
     * */
    public ServiceServiceImpl() {
        servRep=new ServiceRepositoryImpl();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<Service> findAll() {
        return servRep.findAll();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Service findOneById(int id) {
        var finded = servRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int create(Service service) {
        return servRep.save(service);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int update(Service service) {
        return servRep.save(service);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int delete(Service service) {
        return servRep.delete(service);
    }
}
