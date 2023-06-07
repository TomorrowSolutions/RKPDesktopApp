package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * Класс сервиса для категорий
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface CategoryService {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<Category> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Category}
     * */
    Category findOneById(int id);
    /**Функция для добавления
     * @param category выбранная запись
     * @return количество затронутых строк
     * */
    int create(Category category);
    /**Функция для обновления записи
     * @param category выбранная запись
     * @return количество затронутых строк
     * */
    int update(Category category);
    /**Функция для удаления записи
     * @param category выбранная запись
     * @return количество затронутых строк
     * */
    int delete(Category category);
    
}
