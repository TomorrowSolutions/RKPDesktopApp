package ru.kafpin.rkplab8.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.Order;
import ru.kafpin.rkplab8.models.OrderDetail;
import ru.kafpin.rkplab8.models.Service;

import java.util.Map;

public class NewOrderDetailController {

    public ComboBox<Integer> orderCombo;
    public ComboBox<String> serviceCombo;
    public ComboBox<String> objectCombo;
    public TextField quantityTF;

    private Stage stage;
    private Map<Integer, Order> orders;
    private Map<String, Service> services;
    private Map<String, GuardedObject> objects;
    private OrderDetail orderDetail;

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
        if (orderDetail.getOrder()!=null) orderCombo.getSelectionModel().select(orderDetail.getOrder().getId());
        if (orderDetail.getService()!=null) serviceCombo.getSelectionModel().select(orderDetail.getService().getName());
        if (orderDetail.getGuardedObject()!=null) objectCombo.getSelectionModel().select(orderDetail.getGuardedObject().getName());
        quantityTF.setText(String.valueOf(orderDetail.getQuantity()));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setOrders(Map<Integer, Order> orders) {
        this.orders = orders;
        orderCombo.setItems(FXCollections.observableArrayList(orders.keySet()));
        orderCombo.getSelectionModel().select(0);
    }

    public void setServices(Map<String, Service> services) {
        this.services = services;
        serviceCombo.setItems(FXCollections.observableArrayList(services.keySet()));
        serviceCombo.getSelectionModel().select(0);
    }

    public void setObjects(Map<String, GuardedObject> objects) {
        this.objects = objects;
        objectCombo.setItems(FXCollections.observableArrayList(objects.keySet()));
        objectCombo.getSelectionModel().select(0);
    }

    public void onOk(ActionEvent actionEvent) {
        var order = orders.get(orderCombo.getValue());
        orderDetail.setOrder(order);
        orderDetail.setOrderId(order.getId());
        var object = objects.get(objectCombo.getValue());
        orderDetail.setGuardedObject(object);
        orderDetail.setGObjectId(object.getId());
        var service = services.get(serviceCombo.getValue());
        orderDetail.setService(service);
        orderDetail.setServiceId(service.getId());
        try{
            orderDetail.setQuantity(Integer.parseInt(quantityTF.getText()));
        } catch (Exception e){
            orderDetail.setQuantity(0);
        }
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent) {
        stage.close();
    }
}
