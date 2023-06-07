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
/**
 *
 * Класс репозитория для категорий
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface CategoryRepository {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<Category> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Optional} пустой/содержащий запись
     * */
    Optional<Category> findOneById(int id);
    /**Функция для добавления/обновления записи
     * @return количество затронутых строк
     * */
    int save(Category category);
    /**Функция для удаления записи
     * @return количество затронутых строк
     * */
    int delete(Category category);
}
