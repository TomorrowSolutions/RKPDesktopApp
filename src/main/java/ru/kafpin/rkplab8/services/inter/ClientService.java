package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;

public interface ClientService {
    Collection<Client> findAll();
    Client findOneById(int id);
    int create(Client client);
    int update(Client client);
    int delete(int id);
}
