package ru.kafpin.rkplab8.repositories.impl;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.*;
import ru.kafpin.rkplab8.repositories.inter.OrderRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    ClientRepositoryImpl clRep;
    ManagerRepositoryImpl manRep;
    CategoryRepositoryImpl catRep;

    Alert alert;

    public OrderRepositoryImpl() {
        this.clRep = new ClientRepositoryImpl();
        this.manRep = new ManagerRepositoryImpl();
        this.catRep = new CategoryRepositoryImpl();
        this.alert = null;
    }

    @Override
    public Collection<Order> findAll() {
        Collection<Order> orders = null;
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
            orders = mapper(resultSet);
        } catch (SQLException e) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка выполнения запроса");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            Platform.exit();
        }
        return orders;
    }

    @Override
    public Optional<Order> findOneById(int id) {
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.MANAGER_SELECT_ONE);
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            Order order = null;
            while (rs.next()){
                int clientId = rs.getInt("clientId");
                var cl = clRep.findOneById(id);
                Client client = cl.isEmpty() ? null : cl.stream().findFirst().orElse(null);
                int managerId = rs.getInt("managerId");
                var mn = manRep.findOneById(id);
                Manager manager= mn.isEmpty() ? null : mn.stream().findFirst().orElse(null);
                LocalDate dateOfSigning = LocalDate.parse(rs.getString("dateOfSigning"));
                LocalDate dateOfComplete = LocalDate.parse(rs.getString("dateOfSigning"));
                double price  = rs.getDouble("price");
                order=new Order(id,client,clientId,manager,managerId,dateOfSigning,dateOfComplete,price);
            }
            if (order!=null)
                return Optional.of(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(Order order) {
        return 0;
    }

    @Override
    public int delete(Order order) {
        return 0;
    }
    private Collection<Order> mapper(ResultSet resultSet) throws SQLException {
        Collection<Order> orders = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("Id");
            int clientId = resultSet.getInt("clientId");
            var cl = clRep.findOneById(id);
            Client client = cl.isEmpty() ? null : cl.stream().findFirst().orElse(null);
            int managerId = resultSet.getInt("managerId");
            var mn = manRep.findOneById(id);
            Manager manager= mn.isEmpty() ? null : mn.stream().findFirst().orElse(null);
            LocalDate dateOfSigning = LocalDate.parse(resultSet.getString("dateOfSigning"));
            LocalDate dateOfComplete = LocalDate.parse(resultSet.getString("dateOfSigning"));
            double price  = resultSet.getDouble("price");
            orders.add(new Order(id,client,clientId,manager,managerId,dateOfSigning,dateOfComplete,price));
        }
        return orders;
    }
}
