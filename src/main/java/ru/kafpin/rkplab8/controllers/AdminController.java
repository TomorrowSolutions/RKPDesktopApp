package ru.kafpin.rkplab8.controllers;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.kafpin.rkplab8.AdminApp;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.services.impl.ClientServiceImpl;

import java.io.IOException;
import java.util.Collection;

public class AdminController {
    private final ClientServiceImpl clServ = new ClientServiceImpl();
    public TableColumn<Client,Integer> clIdCol;
    public TableColumn<Client,String> clSurnCol;
    public TableColumn<Client,String> clNameCol;
    public TableColumn<Client,String> clPatrCol;
    public TableColumn<Client,String> clPhoneCol;
    public TableColumn<Client,String> clPersCol;
    public TextArea clTxtArea;
    public TableView<Client> clientsTable;
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private void updateClients(){
        clients.clear();
        var fromDB = clServ.findAll();
        for (Client c: fromDB)
            clients.add(c);
    }

    @FXML
    void initialize(){
        updateClients();
        clIdCol.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getId()).asObject());
        clSurnCol.setCellValueFactory(celldata-> new SimpleStringProperty(celldata.getValue().getSurname()));
        clNameCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getName()));
        clPatrCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getName()));
        clPhoneCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getPhoneNum()));
        clPersCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getTypeOfPerson().toString()));
        clientsTable.setItems(clients);
    }
    private void showDialog(Client client) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AdminApp.class.getResource("newmodelviews/new-client.fxml"));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Добавление/обновление клиента");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(AdminApp.getPrimaryStage());
        Scene scene =new Scene(page);
        addStage.setScene(scene);
        NewClientController controller = loader.getController();
        controller.setClient(client);
        controller.setDialogStage(addStage);
        addStage.showAndWait();
    }
    public void onClientInfo(ActionEvent actionEvent) {

    }

    public void onClientDelete(ActionEvent actionEvent) {
        Client client = clientsTable.getSelectionModel().getSelectedItem();
        if (client!=null){
            int rows = clServ.delete(client);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Удаление");
            alert.setHeaderText("Данные успешно удалены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateClients();
        }
    }

    public void onClientUpdate(ActionEvent actionEvent) throws IOException {
        Client client = clientsTable.getSelectionModel().getSelectedItem();
        if (client!=null){
            showDialog(client);
            int rows = clServ.update(client);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Обновление");
            alert.setHeaderText("Данные успешно Обновлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateClients();
        }
    }

    public void onClientCreate(ActionEvent actionEvent) throws IOException {
        Client client = new Client();
        showDialog(client);
        if (client.getName()==""||client.getSurname()==""||client.getPatronymic()==""||client.getPhoneNum()==""||
            client.getCity()==""||client.getStreet()==""||client.getBuilding()==""||client.getAccountnumber()==""){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Не все необходимые данные заполнены");
            alert.showAndWait();
        }
        else {
            int rows = clServ.create(client);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Добавление");
            alert.setHeaderText("Данные успешно добавлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateClients();
        }

    }
}