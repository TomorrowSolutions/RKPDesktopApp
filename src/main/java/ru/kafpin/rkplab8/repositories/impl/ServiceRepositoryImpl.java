package ru.kafpin.rkplab8.repositories.impl;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.Service;
import ru.kafpin.rkplab8.models.Service;
import ru.kafpin.rkplab8.repositories.inter.OrderDetailRepository;
import ru.kafpin.rkplab8.repositories.inter.ServiceRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
/**
 * Реализация {@link ServiceRepository}
 * */
public class ServiceRepositoryImpl implements ServiceRepository {
    Alert alert=null;
    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<Service> findAll() {
        Collection<Service> services = null;
        Statement statement = null;
        try {
            statement = SQLHelper.connection.createStatement();
        } catch (SQLException e) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка создания сессии");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            Platform.exit();
        }
        try {
            ResultSet resultSet = statement.executeQuery(SQLHelper.SERVICE_SELECT_ALL);
            services = mapper(resultSet);
        } catch (SQLException e) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка выполнения запроса");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            Platform.exit();
        }
        return services;
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Optional<Service> findOneById(int id) {
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.SERVICE_SELECT_ONE);
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            Service service =null;
            while (rs.next()){
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                int period = rs.getInt("periodOfExecution");
                service= new Service(id,name,price,period);
            }
            if (service!=null)
                return Optional.of(service);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    /**
     * Метод для определения новая запись или уже существующая
     * @param service передаваемая запись
     * @return {@link PreparedStatement} с соответствующей командой
     * */
    private PreparedStatement InsertOrUpdate(Service service) throws SQLException {
        PreparedStatement statement = null;
        if (service == null) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка создания сессии");
            alert.setContentText("Переданный клиент является пустым");
            alert.showAndWait();
        }
        else {
            if (service.getId()==0){
                statement =  SQLHelper.connection.prepareStatement(SQLHelper.SERVICE_INSERT);
                statement.setString(1,service.getName());
                statement.setDouble(2,service.getPrice());
                statement.setInt(3,service.getPeriodOfExecution());
            }
            else {
                statement = SQLHelper.connection.prepareStatement(SQLHelper.SERVICE_UPDATE);
                statement.setString(1,service.getName());
                statement.setDouble(2,service.getPrice());
                statement.setInt(3,service.getPeriodOfExecution());
                statement.setInt(4,service.getId());
            }
        }
        return statement;
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int save(Service service) {
        PreparedStatement statement = null;
        int rows=0;
        try {
            statement = InsertOrUpdate(service);
            rows=statement.executeUpdate();
        } catch (SQLException e) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка создания сессии");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            Platform.exit();
        }
        return rows;
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int delete(Service service) {
        int rows=0;
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.SERVICE_DELETE);
            statement.setInt(1,service.getId());
            rows=statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
    /** Функция для создания коллекции всех записей
     * @param resultSet данные полученные из базы данных
     * @return {@link Collection} со всеми записями
     * */
    private Collection<Service> mapper(ResultSet resultSet) throws SQLException {
        Collection<Service> services = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("Id");
            String name = resultSet.getString("name");
            Double price = resultSet.getDouble("price");
            int period = resultSet.getInt("periodOfExecution");
            services.add(new Service(id,name,price,period));
        }
        return services;
    }
}
