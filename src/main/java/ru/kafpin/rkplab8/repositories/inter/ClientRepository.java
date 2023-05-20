package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;
import java.util.Optional;

public interface ClientRepository {
    Collection<Client> findAll();
    Optional<Client> findOneById(int id);
    int save(Client client);
    int delete(Client client);

}
