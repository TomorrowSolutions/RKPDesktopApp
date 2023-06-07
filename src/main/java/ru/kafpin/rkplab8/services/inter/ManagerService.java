package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;
/**
 *
 * Класс сервиса для менеджеров
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface ManagerService {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<Manager> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Manager}
     * */
    Manager findOneById(int id);
    /**Функция для добавления
     * @param manager выбранная запись
     * @return количество затронутых строк
     * */
    int create(Manager manager);
    /**Функция для обновления записи
     * @param manager выбранная запись
     * @return количество затронутых строк
     * */
    int update(Manager manager);
    /**Функция для удаления записи
     * @param manager выбранная запись
     * @return количество затронутых строк
     * */
    int delete(Manager manager);
}
