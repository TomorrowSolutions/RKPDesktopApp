package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.models.Service;

import java.util.Collection;
import java.util.Optional;
/**
 *
 * Класс репозитория для услуг
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface ServiceRepository {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<Service> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Optional} пустой/содержащий запись
     * */
    Optional<Service> findOneById(int id);
    /**Функция для добавления/обновления записи
     * @return количество затронутых строк
     * */
    int save(Service service);
    /**Функция для удаления записи
     * @return количество затронутых строк
     * */
    int delete(Service service);
}
