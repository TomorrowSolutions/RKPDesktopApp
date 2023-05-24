package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.repositories.impl.CategoryRepositoryImpl;
import ru.kafpin.rkplab8.services.inter.CategoryService;

import java.util.Collection;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepositoryImpl catRep;

    public CategoryServiceImpl() {
        this.catRep = new CategoryRepositoryImpl();
    }

    @Override
    public Collection<Category> findAll() {
        return catRep.findAll();
    }

    @Override
    public Category findOneById(int id) {
        var finded = catRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }
    @Override
    public int create(Category category) {
        return catRep.save(category);
    }

    @Override
    public int update(Category category) {
        return catRep.save(category);
    }

    @Override
    public int delete(Category category) {
        return catRep.delete(category);
    }
}
