package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;

public interface CategoryService {
    Collection<Category> findAll();
    Category findOneById(int id);
    int create(Category category);
    int update(Category category);
    int delete(Category category);
}
