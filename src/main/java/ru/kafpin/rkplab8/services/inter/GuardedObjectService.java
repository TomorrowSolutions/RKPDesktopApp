package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;
/**
 *
 * Класс сервиса для охраняемых объектов
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface GuardedObjectService {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<GuardedObject> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link GuardedObject}
     * */
    GuardedObject findOneById(int id);
    /**Функция для добавления
     * @param  guardedObject выбранная запись
     * @return количество затронутых строк
     * */
    int create(GuardedObject guardedObject);
    /**Функция для обновления записи
     * @param  guardedObject выбранная запись
     * @return количество затронутых строк
     * */
    int update(GuardedObject guardedObject);
    /**Функция для удаления записи
     * @param  guardedObject выбранная запись
     * @return количество затронутых строк
     * */
    int delete(GuardedObject guardedObject);
}
