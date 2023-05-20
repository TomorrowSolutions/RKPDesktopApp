package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.models.TypeOfPerson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public interface CategoryRepository {
    Collection<Category> findAll();
    Optional<Category> findOneById(int id);
    int save(Category category);
    int delete(Category category);
}
