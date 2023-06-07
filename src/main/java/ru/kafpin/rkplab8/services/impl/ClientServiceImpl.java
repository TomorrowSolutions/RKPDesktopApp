package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.repositories.impl.CategoryRepositoryImpl;
import ru.kafpin.rkplab8.repositories.impl.ClientRepositoryImpl;
import ru.kafpin.rkplab8.repositories.inter.ClientRepository;
import ru.kafpin.rkplab8.services.inter.CategoryService;
import ru.kafpin.rkplab8.services.inter.ClientService;

import java.util.Collection;
/**
 * Реализация {@link ClientService}
 * */
public class ClientServiceImpl implements ClientService {
    /**
     * Объект {@link ClientRepositoryImpl} для взаимодействия с репозиторием
     * */
    private final ClientRepository clRep;
    /**
     * Конструктор для заполнения объекта {@link ClientRepositoryImpl}
     * */
    public ClientServiceImpl() {
        clRep=new ClientRepositoryImpl();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<Client> findAll() {
        return clRep.findAll();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Client findOneById(int id) {
        var finded = clRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int create(Client client) {
        return clRep.save(client);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int update(Client client) {
        return clRep.save(client);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int delete(Client client) {
        return clRep.delete(client);
    }
}
