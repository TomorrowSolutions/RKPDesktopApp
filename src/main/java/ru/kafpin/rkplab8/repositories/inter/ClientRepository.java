package ru.kafpin.rkplab8.repositories.inter;

import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Manager;

import java.util.Collection;
import java.util.Optional;
/**
 *
 * Класс репозитория для клиентов
 * @author Вадим Гуреев
 * @version 1.0
 */
public interface ClientRepository {
    /**Функция для выборки всех записей
     * @return {@link Collection} со всеми записями
     * */
    Collection<Client> findAll();
    /**Функция для выборки одной записи
     * @param id идентификатор записи
     * @return {@link Optional} пустой/содержащий запись
     * */
    Optional<Client> findOneById(int id);
    /**Функция для добавления/обновления записи
     * @return количество затронутых строк
     * */
    int save(Client client);
    /**Функция для удаления записи
     * @return количество затронутых строк
     * */
    int delete(Client client);

}
