package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.GuardedObject;

import java.util.Collection;
import java.util.Optional;
/**
 *
 * Класс репозитория для охраняемых объектов
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface GuardedObjectRepository {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<GuardedObject> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Optional} пустой/содержащий запись
     * */
    Optional<GuardedObject> findOneById(int id);
    /**Функция для добавления/обновления записи
     * @return количество затронутых строк
     * */
    int save(GuardedObject gObject);
    /**Функция для удаления записи
     * @return количество затронутых строк
     * */
    int delete(GuardedObject gObject);
}
