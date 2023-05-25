package ru.kafpin.rkplab8.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.models.Order;

import java.time.LocalDate;
import java.util.Map;

public class NewOrderController {
    public ComboBox<String> managerCombo;
    public ComboBox<String> clientCombo;
    public DatePicker signingDP;
    public DatePicker completeDP;
    public TextField priceTF;

    private Stage stage;
    private Map<String, Manager> managers;
    private Map<String, Client> clients;

    public void setOrder(Order order) {
        this.order = order;
        if (order.getManager()!=null) managerCombo.getSelectionModel().select(order.getManager().getName());
        if(order.getClient()!=null) clientCombo.getSelectionModel().select(order.getClient().getName());
        if (order.getDateOfSigning()!=null) signingDP.setValue(order.getDateOfSigning());
        else signingDP.setValue(LocalDate.now());
        if (order.getDateOfComplete()!=null) completeDP.setValue(order.getDateOfComplete());
        else signingDP.setValue(LocalDate.now());
        priceTF.setText(String.valueOf(order.getPrice()));
    }

    private Order order;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setManagers(Map<String, Manager> managers) {
        this.managers = managers;
        managerCombo.setItems(FXCollections.observableArrayList(managers.keySet()));
        managerCombo.getSelectionModel().select(0);
    }

    public void setClients(Map<String, Client> clients) {
        this.clients = clients;
        clientCombo.setItems(FXCollections.observableArrayList(clients.keySet()));
        clientCombo.getSelectionModel().select(0);
    }

    public void onOk(ActionEvent actionEvent) {
        var client = clients.get(clientCombo.getValue());
        order.setClient(client);
        order.setClientId(client.getId());
        var manager = managers.get(managerCombo.getValue());
        order.setManager(manager);
        order.setManagerId(manager.getId());
        order.setDateOfSigning(signingDP.getValue());
        order.setDateOfComplete(completeDP.getValue());
        try{
            order.setPrice(Double.parseDouble(priceTF.getText()));
        } catch (Exception e){
            order.setPrice(0);
        }
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent) {
        stage.close();
    }
}
