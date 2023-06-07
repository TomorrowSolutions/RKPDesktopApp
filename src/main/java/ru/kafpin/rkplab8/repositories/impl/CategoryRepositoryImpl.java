package ru.kafpin.rkplab8.repositories.impl;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.repositories.inter.CategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
/**
 * Реализация {@link CategoryRepository}
 * */
public class CategoryRepositoryImpl implements CategoryRepository {
    private Alert alert =null;
    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<Category> findAll() {
        Collection<Category> categories = null;
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
            ResultSet resultSet = statement.executeQuery(SQLHelper.CATEGORY_SELECT_ALL);
            categories = mapper(resultSet);
        } catch (SQLException e) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка выполнения запроса");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            Platform.exit();
        }
        return categories;
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Optional<Category> findOneById(int id) {
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.CATEGORY_SELECT_ONE);
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            Category category =null;
            while (rs.next()){
                int cattid = rs.getInt("Id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                category = new Category(id,name,salary);
            }
            if (category!=null)
                return Optional.of(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    /**
     * Метод для определения новая запись или уже существующая
     * @param category передаваемая запись
     * @return {@link PreparedStatement} с соответствующей командой
     * */
    private PreparedStatement InsertOrUpdate(Category category) throws SQLException {
        PreparedStatement statement = null;
        if (category == null) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка создания сессии");
            alert.setContentText("Переданный клиент является пустым");
            alert.showAndWait();
        }
        else {
            if (category.getId()==0){
                statement =  SQLHelper.connection.prepareStatement(SQLHelper.CATEGORY_INSERT);
                statement.setString(1, category.getName());
                statement.setDouble(2,category.getSalary());
            }
            else {
                statement = SQLHelper.connection.prepareStatement(SQLHelper.CATEGORY_UPDATE);
                statement.setString(1, category.getName());
                statement.setDouble(2, category.getSalary());
                statement.setInt(3, category.getId());
            }
        }
        return statement;
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int save(Category category) {
        PreparedStatement statement = null;
        int rows=0;
        try {
            statement = InsertOrUpdate(category);
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
    public int delete(Category category) {
        int rows=0;
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.CATEGORY_DELETE);
            statement.setInt(1,category.getId());
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
    private Collection<Category> mapper(ResultSet resultSet) throws SQLException {
        Collection<Category> categories = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("Id");
            String name = resultSet.getString("name");
            double salary = resultSet.getDouble("salary");
            categories.add(new Category(id,name,salary));
        }
        return categories;
    }
}
