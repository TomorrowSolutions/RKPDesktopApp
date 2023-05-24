package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.repositories.impl.ClientRepositoryImpl;
import ru.kafpin.rkplab8.repositories.inter.ClientRepository;
import ru.kafpin.rkplab8.services.inter.ClientService;

import java.util.Collection;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clRep;

    public ClientServiceImpl() {
        clRep=new ClientRepositoryImpl();
    }

    @Override
    public Collection<Client> findAll() {
        return clRep.findAll();
    }

    @Override
    public Client findOneById(int id) {
        var finded = clRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }

    @Override
    public int create(Client client) {
        return clRep.save(client);
    }

    @Override
    public int update(Client client) {
        return clRep.save(client);
    }

    @Override
    public int delete(Client client) {
        return clRep.delete(client);
    }
}
