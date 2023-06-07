package ru.kafpin.rkplab8.repositories.impl;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.repositories.inter.CategoryRepository;
import ru.kafpin.rkplab8.repositories.inter.GuardedObjectRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
/**
 * Реализация {@link GuardedObjectRepository}
 * */
public class GuardedObjectRepositoryImpl  implements GuardedObjectRepository {
    Alert alert =null;
    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<GuardedObject> findAll() {
        Collection<GuardedObject> gObjects = null;
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
            ResultSet resultSet = statement.executeQuery(SQLHelper.GUARDEDOBJECT_SELECT_ALL);
            gObjects = mapper(resultSet);
        } catch (SQLException e) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка выполнения запроса");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            Platform.exit();
        }
        return gObjects;
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Optional<GuardedObject> findOneById(int id) {
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.GUARDEDOBJECT_SELECT_ONE);
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            GuardedObject gObject =null;
            while (rs.next()){
                String name = rs.getString("name");
                String image = rs.getString("image");
                String city = rs.getString("city");
                String street = rs.getString("street");
                String building = rs.getString("building");
                gObject = new GuardedObject(id,name,image,city,street,building);
            }
            if (gObject!=null)
                return Optional.of(gObject);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    /**
     * Метод для определения новая запись или уже существующая
     * @param gObject передаваемая запись
     * @return {@link PreparedStatement} с соответствующей командой
     * */
    private PreparedStatement InsertOrUpdate(GuardedObject gObject) throws SQLException {
        PreparedStatement statement = null;
        if (gObject == null) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка создания сессии");
            alert.setContentText("Переданный клиент является пустым");
            alert.showAndWait();
        }
        else {
            if (gObject.getId()==0){
                statement =  SQLHelper.connection.prepareStatement(SQLHelper.GUARDEDOBJECT_INSERT);
                statement.setString(1, gObject.getName());
                statement.setString(2,gObject.getImage());
                statement.setString(3,gObject.getCity());
                statement.setString(4,gObject.getStreet());
                statement.setString(5,gObject.getBuilding());
            }
            else {
                statement = SQLHelper.connection.prepareStatement(SQLHelper.GUARDEDOBJECT_UPDATE);
                statement.setString(1, gObject.getName());
                statement.setString(2,gObject.getImage());
                statement.setString(3,gObject.getCity());
                statement.setString(4,gObject.getStreet());
                statement.setString(5,gObject.getBuilding());
                statement.setInt(6,gObject.getId());
            }
        }
        return statement;
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int save(GuardedObject gObject) {
        PreparedStatement statement = null;
        int rows=0;
        try {
            statement = InsertOrUpdate(gObject);
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
    public int delete(GuardedObject gObject) {
        int rows=0;
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.GUARDEDOBJECT_DELETE);
            statement.setInt(1,gObject.getId());
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
    private Collection<GuardedObject> mapper(ResultSet resultSet) throws SQLException {
        Collection<GuardedObject> gObjects = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("Id");
            String name = resultSet.getString("name");
            String image = resultSet.getString("image");
            String city = resultSet.getString("city");
            String street = resultSet.getString("street");
            String building = resultSet.getString("building");
            gObjects.add(new GuardedObject(id,name,image,city,street,building));
        }
        return gObjects;
    }
}
