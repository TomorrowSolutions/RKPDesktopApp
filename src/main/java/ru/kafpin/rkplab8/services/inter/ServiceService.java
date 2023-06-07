package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.Order;
import ru.kafpin.rkplab8.models.Service;

import java.util.Collection;
/**
 *
 * Класс сервиса для услуг
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface ServiceService {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<Service> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Service}
     * */
    Service findOneById(int id);
    /**Функция для добавления
     * @param  service выбранная запись
     * @return количество затронутых строк
     * */
    int create(Service service);
    /**Функция для обновления записи
     * @param  service выбранная запись
     * @return количество затронутых строк
     * */
    int update(Service service);
    /**Функция для удаления записи
     * @param  service выбранная запись
     * @return количество затронутых строк
     * */
    int delete(Service service);
}
