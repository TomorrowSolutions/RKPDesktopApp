package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.OrderDetail;
import ru.kafpin.rkplab8.models.Service;

import java.util.Collection;
import java.util.Optional;
/**
 *
 * Класс репозитория для подробностей заказа
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface OrderDetailRepository {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<OrderDetail> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Optional} пустой/содержащий запись
     * */
    Optional<OrderDetail> findOneById(int id);
    /**Функция для добавления/обновления записи
     * @return количество затронутых строк
     * */
    int save(OrderDetail orderDetail);
    /**Функция для удаления записи
     * @return количество затронутых строк
     * */
    int delete(OrderDetail orderDetail);
}
