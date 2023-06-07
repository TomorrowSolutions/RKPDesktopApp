package ru.kafpin.rkplab8.repositories.impl;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.repositories.inter.ClientRepository;
import ru.kafpin.rkplab8.repositories.inter.ManagerRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
/**
 * Реализация {@link ManagerRepository}
 * */
public class ManagerRepositoryImpl implements ManagerRepository {
    /**
     * Объект {@link CategoryRepositoryImpl} для внешнего ключа
     * */
    CategoryRepositoryImpl catRep;
    private Alert alert;
    /**
     * Конструктор для заполнения объекта {@link CategoryRepositoryImpl}
     * */
    public ManagerRepositoryImpl() {
        this.catRep = new CategoryRepositoryImpl();
        this.alert = null;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection<Manager> findAll() {
        Collection<Manager> managers = null;
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
            ResultSet resultSet = statement.executeQuery(SQLHelper.MANAGER_SELECT_ALL);
            managers = mapper(resultSet);
        } catch (SQLException e) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка выполнения запроса");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            Platform.exit();
        }
        return managers;
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public Optional<Manager> findOneById(int id) {
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.MANAGER_SELECT_ONE);
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            Manager manager =null;
            while (rs.next()){
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");
                String education = rs.getString("education");
                int categoryId = rs.getInt("categoryId");
                var cat = catRep.findOneById(categoryId);
                Category category = cat.isEmpty() ? null : cat.stream().findFirst().orElse(null);
                LocalDate dateOfStart = LocalDate.parse(rs.getString("dateOfStart"));
                String accountNumber = rs.getString("accountNumber");
                manager= new Manager(id,surname,name,patronymic,education,category,categoryId,dateOfStart,accountNumber);
            }
            if (manager!=null)
                return Optional.of(manager);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    /**
     * Метод для определения новая запись или уже существующая
     * @param manager передаваемая запись
     * @return {@link PreparedStatement} с соответствующей командой
     * */
    private PreparedStatement InsertOrUpdate(Manager manager) throws SQLException {
        PreparedStatement pstm = null;
        if (manager == null) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка создания сессии");
            alert.setContentText("Переданный клиент является пустым");
            alert.showAndWait();
        }
        else {
            if (manager.getId()==0){
                pstm = SQLHelper.connection.prepareStatement(SQLHelper.MANAGER_INSERT);
                pstm.setString(1,manager.getSurname());
                pstm.setString(2,manager.getName());
                pstm.setString(3,manager.getPatronymic());
                pstm.setString(4,manager.getEducation());
                pstm.setInt(5,manager.getCategoryId());
                pstm.setString(6,manager.getDateOfStart().toString());
                pstm.setString(7,manager.getAccountNumber());
            }
            else {
                pstm = SQLHelper.connection.prepareStatement(SQLHelper.MANAGER_UPDATE);
                pstm.setString(1,manager.getSurname());
                pstm.setString(2,manager.getName());
                pstm.setString(3,manager.getPatronymic());
                pstm.setString(4,manager.getEducation());
                pstm.setInt(5,manager.getCategoryId());
                pstm.setString(6,manager.getDateOfStart().toString());
                pstm.setString(7,manager.getAccountNumber());
                pstm.setInt(8,manager.getId());
            }
        }
        return pstm;
    }
    /**
     * {@inheritDoc}
     * */
    @Override
    public int save(Manager manager) {
        PreparedStatement statement = null;
        int rows=0;
        try {
            statement = InsertOrUpdate(manager);
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
    public int delete(Manager manager) {
        int rows=0;
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.MANAGER_DELETE);
            statement.setInt(1,manager.getId());
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
    private Collection<Manager> mapper(ResultSet resultSet) throws SQLException {
        Collection<Manager> managers = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("Id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String patronymic = resultSet.getString("patronymic");
            String education = resultSet.getString("education");
            int categoryId = resultSet.getInt("categoryId");
            var cat = catRep.findOneById(categoryId);
            Category category = cat.isEmpty() ? null : cat.stream().findFirst().orElse(null);
            LocalDate dateOfStart = LocalDate.parse(resultSet.getString("dateOfStart"));
            String accountNumber = resultSet.getString("accountNumber");
            managers.add(new Manager(id,surname,name,patronymic,education,category,categoryId,dateOfStart,accountNumber));
        }
        return managers;
    }
}
