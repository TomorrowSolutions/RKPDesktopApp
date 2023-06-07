package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Order;

import java.util.Collection;
import java.util.Optional;
/**
 *
 * Класс репозитория для заказов
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface OrderRepository {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<Order> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Optional} пустой/содержащий запись
     * */
    Optional<Order> findOneById(int id);
    /**Функция для добавления/обновления записи
     * @return количество затронутых строк
     * */
    int save(Order order);
    /**Функция для удаления записи
     * @return количество затронутых строк
     * */
    int delete(Order order);
}
