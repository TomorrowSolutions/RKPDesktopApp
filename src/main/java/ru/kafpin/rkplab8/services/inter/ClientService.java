package ru.kafpin.rkplab8.services.inter;

import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;
/**
 *
 * Класс сервиса для клиентов
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface ClientService {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<Client> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Client}
     * */
    Client findOneById(int id);
    /**Функция для добавления
     * @param  client выбранная запись
     * @return количество затронутых строк
     * */
    int create(Client client);
    /**Функция для обновления записи
     * @param  client выбранная запись
     * @return количество затронутых строк
     * */
    int update(Client client);
    /**Функция для удаления записи
     * @param  client выбранная запись
     * @return количество затронутых строк
     * */
    int delete(Client client);
}
