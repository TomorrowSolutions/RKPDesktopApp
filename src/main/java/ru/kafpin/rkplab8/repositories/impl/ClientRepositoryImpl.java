package ru.kafpin.rkplab8.repositories.impl;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.TypeOfPerson;
import ru.kafpin.rkplab8.repositories.inter.ClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {
    private Alert alert =null;


    @Override
    public Collection<Client> findAll() {
        Collection<Client> Clients = null;
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
            ResultSet resultSet = statement.executeQuery(SQLHelper.CLIENT_SELECT_ALL);
            Clients = mapper(resultSet);
        } catch (SQLException e) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка выполнения запроса");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            Platform.exit();
        }
        return Clients;
    }

    @Override
    public Optional<Client> findOneById(int id) {
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.CLIENT_SELECT_ONE);
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            Client client =null;
            while (rs.next()){
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");
                String phonenum = rs.getString("phonenum");
                TypeOfPerson tp =rs.getString("typeOfPerson").equals("INDIVIDUAL") ? TypeOfPerson.INDIVIDUAL: TypeOfPerson.LEGAL;
                String city = rs.getString("city");
                String street = rs.getString("street");
                String building = rs.getString("building");
                String accnum = rs.getString("accountnum");
                client = new Client(id,surname,name,patronymic,phonenum,tp,city,street,building,accnum);
            }
            if (client!=null)
                return Optional.of(client);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    private PreparedStatement InsertOrUpdate(Client client) throws SQLException {
        PreparedStatement statement = null;
        if (client == null) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка создания сессии");
            alert.setContentText("Переданный клиент является пустым");
            alert.showAndWait();
        }
        else {
            if (client.getId()==0){
                statement = SQLHelper.connection.prepareStatement(SQLHelper.CLIENT_INSERT);
                statement.setString(1, client.getSurname());
                statement.setString(2, client.getName());
                statement.setString(3, client.getPatronymic());
                statement.setString(4, client.getPhoneNum());
                statement.setString(5, client.getTypeOfPerson().toString());
                statement.setString(6, client.getCity());
                statement.setString(7, client.getStreet());
                statement.setString(8, client.getBuilding());
                statement.setString(9, client.getAccountnumber());
            }
            else {
                statement = SQLHelper.connection.prepareStatement(SQLHelper.CLIENT_UPDATE);
                statement.setString(1, client.getName());
                statement.setString(2, client.getSurname());
                statement.setString(3, client.getPatronymic());
                statement.setString(4, client.getPhoneNum());
                statement.setString(5, client.getTypeOfPerson().toString());
                statement.setString(6, client.getCity());
                statement.setString(7, client.getStreet());
                statement.setString(8, client.getBuilding());
                statement.setString(9, client.getAccountnumber());
                statement.setInt(10, client.getId());
            }
        }
        return statement;
    }
    @Override
    public int save(Client client) {
        PreparedStatement statement = null;
        int rows=0;
        try {
            statement = InsertOrUpdate(client);
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

    @Override
    public int delete(Client client) {
        int rows=0;
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.CLIENT_DELETE);
            statement.setInt(1,client.getId());
            rows=statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
    private Collection<Client> mapper(ResultSet resultSet) throws SQLException {
        Collection<Client> clients = new ArrayList<>();
        Client client;
        while (resultSet.next()){
            int id = resultSet.getInt("Id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String patronymic = resultSet.getString("patronymic");
            String phonenum = resultSet.getString("phonenum");
            TypeOfPerson tp =resultSet.getString("typeOfPerson").equals("INDIVIDUAL") ? TypeOfPerson.INDIVIDUAL: TypeOfPerson.LEGAL;
            String city = resultSet.getString("city");
            String street = resultSet.getString("street");
            String building = resultSet.getString("building");
            String accnum = resultSet.getString("accountnum");
            clients.add(new Client(id,surname,name,patronymic,phonenum,tp,city,street,building,accnum));
        }
        return clients;
    }
}
