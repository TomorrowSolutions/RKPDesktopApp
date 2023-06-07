package ru.kafpin.rkplab8.services.impl;

import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.repositories.impl.CategoryRepositoryImpl;
import ru.kafpin.rkplab8.repositories.impl.ClientRepositoryImpl;
import ru.kafpin.rkplab8.repositories.impl.ManagerRepositoryImpl;
import ru.kafpin.rkplab8.repositories.inter.CategoryRepository;
import ru.kafpin.rkplab8.services.inter.CategoryService;

import java.util.Collection;
/**
 * Реализация {@link CategoryService}
 * */
public class CategoryServiceImpl implements CategoryService {
    /**
     * Объект {@link CategoryRepositoryImpl} для взаимодействия с репозиторием
     * */
    private final CategoryRepositoryImpl catRep;
    /**
     * Конструктор для заполнения объекта {@link CategoryRepositoryImpl}
     * */
    public CategoryServiceImpl() {
        this.catRep = new CategoryRepositoryImpl();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<Category> findAll() {
        return catRep.findAll();
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Category findOneById(int id) {
        var finded = catRep.findOneById(id);
        if(finded.isEmpty())
            return null;
        return finded.stream().findFirst().orElse(null);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int create(Category category) {
        return catRep.save(category);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int update(Category category) {
        return catRep.save(category);
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int delete(Category category) {
        return catRep.delete(category);
    }
}
