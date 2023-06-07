package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;
import java.util.Optional;
/**
 *
 * Класс репозитория для менеджеров
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface ManagerRepository {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<Manager> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Optional} пустой/содержащий запись
     * */
    Optional<Manager> findOneById(int id);
    /**Функция для добавления/обновления записи
     * @return количество затронутых строк
     * */
    int save(Manager manager);
    /**Функция для удаления записи
     * @return количество затронутых строк
     * */
    int delete(Manager manager);
}
