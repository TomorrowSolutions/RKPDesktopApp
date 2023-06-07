package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Order;
import ru.kafpin.rkplab8.models.OrderDetail;

import java.util.Collection;
/**
 *
 * Класс сервиса для заказов
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface OrderService {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<Order> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Order}
     * */
    Order findOneById(int id);
    /**Функция для добавления
     *  @param  order выбранная запись
     * @return количество затронутых строк
     * */
    int create(Order order);
    /**Функция для обновления записи
     *  @param  order выбранная запись
     * @return количество затронутых строк
     * */
    int update(Order order);
    /**Функция для удаления записи
     * @param  order выбранная запись
     * @return количество затронутых строк
     * */
    int delete(Order order);}
