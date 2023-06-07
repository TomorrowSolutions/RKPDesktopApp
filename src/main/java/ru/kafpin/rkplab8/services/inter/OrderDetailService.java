package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.models.OrderDetail;

import java.util.Collection;
/**
 *
 * Класс сервиса для подробностей заказа
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface OrderDetailService {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<OrderDetail> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link OrderDetail}
     * */
    OrderDetail findOneById(int id);
    /**Функция для добавления
     * @param  orderDetail выбранная запись
     * @return количество затронутых строк
     * */
    int create(OrderDetail orderDetail);
    /**Функция для обновления записи
     * @param  orderDetail выбранная запись
     * @return количество затронутых строк
     * */
    int update(OrderDetail orderDetail);
    /**Функция для удаления записи
     * @param  orderDetail выбранная запись
     * @return количество затронутых строк
     * */
    int delete(OrderDetail orderDetail);
}
