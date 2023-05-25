package ru.kafpin.rkplab8.controllers;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
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
import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.models.Order;
import ru.kafpin.rkplab8.repositories.impl.OrderRepositoryImpl;
import ru.kafpin.rkplab8.services.impl.CategoryServiceImpl;
import ru.kafpin.rkplab8.services.impl.ClientServiceImpl;
import ru.kafpin.rkplab8.services.impl.ManagerServiceImpl;
import ru.kafpin.rkplab8.services.impl.OrderServiceImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class AdminController {



    //region Таблица клиентов
    private final ClientServiceImpl clServ = new ClientServiceImpl();
    public TableColumn<Client,Integer> clIdCol;
    public TableColumn<Client,String> clSurnCol;
    public TableColumn<Client,String> clNameCol;
    public TableColumn<Client,String> clPatrCol;
    public TableColumn<Client,String> clPhoneCol;
    public TableColumn<Client,String> clPersCol;
    public TextArea clTxtArea;
    public TableView<Client> clientsTable;
    public TableView<Manager> managersTable;
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    //endregion

    //region Таблица категорий
    private final CategoryServiceImpl catServ = new CategoryServiceImpl();
    public TableColumn<Category,Integer> catIdCol;
    public TableColumn<Category,String> catNameCol;
    public TableColumn<Category,Double> catSalaryCol;
    public TableView<Category> categoriesTable;
    private ObservableList<Category> categories = FXCollections.observableArrayList();
    //endregion

    //region Таблица менеджеров
    private final ManagerServiceImpl manServ = new ManagerServiceImpl();
    public TableColumn<Manager,Integer> manIdCol;
    public TableColumn<Manager,String> manSurnCol;
    public TableColumn<Manager,String> manNameCol;
    public TableColumn<Manager,String> manPatrCol;
    public TableColumn<Manager,String> manCatCol;

    private ObservableList<Manager> managers = FXCollections.observableArrayList();
    //endregion

    //region Таблица заказов
    private final OrderServiceImpl ordServ = new OrderServiceImpl();
    public TableView<Order> ordersTable;
    public TableColumn<Order,Integer> ordIdCol;
    public TableColumn<Order,String> ordClientCol;
    public TableColumn<Order,String> ordManCol;
    public TableColumn<Order, String> ordDateCol;
    private ObservableList<Order> orders = FXCollections.observableArrayList();
    //endregion

    private void updateClients(){
        clients.clear();
        var fromDB = clServ.findAll();
        for (Client c: fromDB)
            clients.add(c);
    }
    private void updateCategories(){
        categories.clear();
        var fromDB = catServ.findAll();
        for (Category c : fromDB)
            categories.add(c);
    }
    private void updateManagers(){
        managers.clear();
        var fromDB = manServ.findAll();
        for (Manager m: fromDB)
            managers.add(m);
    }
    private void updateOrders(){
        orders.clear();
        var fromDB = ordServ.findAll();
        for (Order o: fromDB)
            orders.add(o);
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

        updateCategories();
        catIdCol.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getId()).asObject());
        catNameCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getName()));
        catSalaryCol.setCellValueFactory(celldata -> new SimpleDoubleProperty(celldata.getValue().getSalary()).asObject());
        categoriesTable.setItems(categories);

        updateManagers();
        manIdCol.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getId()).asObject());
        manNameCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getName()));
        manSurnCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getSurname()));
        manPatrCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getPatronymic()));
        manCatCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getCategory().getName()));
        managersTable.setItems(managers);

        updateOrders();
        ordIdCol.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getId()).asObject());
        ordClientCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getClient().getFIO()));
        ordManCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getManager().getFIO()));
        ordDateCol.setCellValueFactory(celldate -> new SimpleStringProperty(celldate.getValue().getDateOfSigning().toString()));
        ordersTable.setItems(orders);
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
    private void showDialog(Category category) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AdminApp.class.getResource("newmodelviews/new-category.fxml"));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Добавление/обновление категории");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(AdminApp.getPrimaryStage());
        Scene scene =new Scene(page);
        addStage.setScene(scene);
        NewCategoryController controller = loader.getController();
        controller.setCategory(category);
        controller.setStage(addStage);
        addStage.showAndWait();
    }
    private void showDialog(Manager manager) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AdminApp.class.getResource("newmodelviews/new-manager.fxml"));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Добавление/обновление менеджера");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(AdminApp.getPrimaryStage());
        Scene scene =new Scene(page);
        addStage.setScene(scene);
        NewManagerController controller = loader.getController();
        controller.setManager(manager);
        HashMap<String,Category> hashMap =new HashMap<>();
        for (Category c: categories)
            hashMap.put(c.getName(),c);
        controller.setCategories(hashMap);
        controller.setStage(addStage);
        addStage.showAndWait();
    }
    private void showDialog(Order order) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AdminApp.class.getResource("newmodelviews/new-order.fxml"));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Добавление/обновление менеджера");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(AdminApp.getPrimaryStage());
        Scene scene =new Scene(page);
        addStage.setScene(scene);
        NewOrderController controller = loader.getController();
        controller.setOrder(order);
        HashMap<String,Manager> hashMan =new HashMap<>();
        for (Manager m: managers)
            hashMan.put(m.getFIO(),m);
        controller.setManagers(hashMan);
        HashMap<String,Client> hashCl =new HashMap<>();
        for (Client c : clients)
            hashCl.put(c.getFIO(),c);
        controller.setClients(hashCl);
        controller.setStage(addStage);
        addStage.showAndWait();
    }


    //region Действия клиентов

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
    //endregion

    //region Действия категорий
    public void onCatCreate(ActionEvent actionEvent) throws IOException {
        Category category = new Category();
        showDialog(category);
        if (category.getName()==""||category.getSalary()==0){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Не все необходимые данные заполнены");
            alert.showAndWait();
        }
        else {
            int rows = catServ.create(category);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Добавление");
            alert.setHeaderText("Данные успешно добавлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateCategories();
        }
    }

    public void onCatUpdate(ActionEvent actionEvent) throws IOException {
        Category category = categoriesTable.getSelectionModel().getSelectedItem();
        if (category!=null){
            showDialog(category);
            int rows = catServ.update(category);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Обновление");
            alert.setHeaderText("Данные успешно Обновлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateCategories();
        }
    }

    public void onCatDelete(ActionEvent actionEvent) {
        Category category = categoriesTable.getSelectionModel().getSelectedItem();
        if (category!=null){
            int rows = catServ.delete(category);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Удаление");
            alert.setHeaderText("Данные успешно удалены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateCategories();
        }
    }

    public void onCatInfo(ActionEvent actionEvent) {
    }
    //endregion

    //region Действия менеджеров
    public void onManCreate(ActionEvent actionEvent) throws IOException {
        Manager manager = new Manager();
        showDialog(manager);
        if (manager.getName()==null||manager.getSurname()==null||manager.getPatronymic()==null||manager.getAccountNumber()==null||
                manager.getEducation()==null){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Не все необходимые данные заполнены");
            alert.showAndWait();
        }
        else {
            int rows = manServ.create(manager);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Добавление");
            alert.setHeaderText("Данные успешно добавлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateManagers();
        }
    }

    public void onManUpdate(ActionEvent actionEvent) throws IOException {
        Manager manager = managersTable.getSelectionModel().getSelectedItem();
        if (manager!=null){
            showDialog(manager);
            int rows = manServ.update(manager);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Обновление");
            alert.setHeaderText("Данные успешно Обновлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateManagers();
        }
    }

    public void onManDelete(ActionEvent actionEvent) {
        Manager manager = managersTable.getSelectionModel().getSelectedItem();
        if (manager!=null){
            int rows = manServ.delete(manager);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Обновление");
            alert.setHeaderText("Данные успешно удалены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateManagers();
        }
    }

    public void onManInfo(ActionEvent actionEvent) {
    }
    //endregion

    public void onOrdCreate(ActionEvent actionEvent) throws IOException {
        Order order = new Order();
        showDialog(order);
        if (order.getClient()==null||order.getManager()==null){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Не все необходимые данные заполнены");
            alert.showAndWait();
        }
        else {
            int rows = ordServ.create(order);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Добавление");
            alert.setHeaderText("Данные успешно добавлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateManagers();
        }
    }

    public void onOrdUpdate(ActionEvent actionEvent) throws IOException {
        Order order = ordersTable.getSelectionModel().getSelectedItem();
        if (order!=null){
            showDialog(order);
            int rows = ordServ.update(order);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Обновление");
            alert.setHeaderText("Данные успешно Обновлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateManagers();
        }
    }

    public void onOrdDelete(ActionEvent actionEvent) {
        Order order = ordersTable.getSelectionModel().getSelectedItem();
        if (order!=null){
            int rows = ordServ.delete(order);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Обновление");
            alert.setHeaderText("Данные успешно удалены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateManagers();
        }
    }

    public void onOrdInfo(ActionEvent actionEvent) {
    }


}