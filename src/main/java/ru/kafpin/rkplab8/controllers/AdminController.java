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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.kafpin.rkplab8.AdminApp;
import ru.kafpin.rkplab8.models.*;
import ru.kafpin.rkplab8.services.impl.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.*;

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
    public TableView<Manager> managersTable;
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

    //region Таблица услуг
    private final ServiceServiceImpl servServ = new ServiceServiceImpl();
    public TableView<Service> servicesTable;
    public TableColumn<Service,Integer> servIdCol;
    public TableColumn<Service,String> servNameCol;
    public TableColumn<Service,Integer> servPerCol;
    public TableColumn<Service,Double> servPriceCol;
    private ObservableList<Service> services = FXCollections.observableArrayList();
    //endregion

    //region Таблица объектов
    private final GuardedObjectServiceImpl objServ = new GuardedObjectServiceImpl();
    public TableView<GuardedObject> objectsTable;
    public TableColumn<GuardedObject,Integer> objIdCol;
    public TableColumn<GuardedObject,String> objNameCol;
    public TableColumn<GuardedObject,String> objCityCol;
    public TableColumn<GuardedObject,String> objStreetCol;
    public TableColumn<GuardedObject,String> objBuildCol;
    private ObservableList<GuardedObject> guardedObjects =FXCollections.observableArrayList();
    public ImageView objImgView;
    //endregion

    //region Подробности заказов
    public TableColumn<OrderDetail,Integer> odIdCol;
    public TableColumn<OrderDetail,Integer> odOrdCol;
    public TableColumn<OrderDetail,String> odObjCol;
    public TableColumn<OrderDetail,String> odServCol;
    public TableColumn<OrderDetail,Integer> odQuanCol;
    public TableView<OrderDetail> ordDetTable;
    private final OrderDetailServiceImpl odServ = new OrderDetailServiceImpl();
    private ObservableList<OrderDetail> orderDetails = FXCollections.observableArrayList();
    //endregion

    //region Updates
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
    private void updateOrderDetails(){
        orderDetails.clear();
        var fromDB = odServ.findAll();
        for (OrderDetail od: fromDB)
            orderDetails.add(od);
    }
    private void updateServices(){
        services.clear();
        var fromDb = servServ.findAll();
        for (Service s : fromDb)
            services.add(s);
    }
    private void updateObjects(){
        guardedObjects.clear();
        var fromDb = objServ.findAll();
        for (GuardedObject obj : fromDb)
            guardedObjects.add(obj);
    }
    //endregion
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

        updateServices();
        servIdCol.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getId()).asObject());
        servNameCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getName()));
        servPerCol.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getPeriodOfExecution()).asObject());
        servPriceCol.setCellValueFactory(celldata -> new SimpleDoubleProperty(celldata.getValue().getPrice()).asObject());
        servicesTable.setItems(services);

        updateObjects();
        objIdCol.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getId()).asObject());
        objNameCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getName()));
        objCityCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getCity()));
        objStreetCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getStreet()));
        objBuildCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getBuilding()));
        objectsTable.setItems(guardedObjects);

        updateOrderDetails();
        odIdCol.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getId()).asObject());
        odOrdCol.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getOrderId()).asObject());
        odObjCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getGuardedObject().getName()));
        odServCol.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getService().getName()));
        odQuanCol.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getQuantity()).asObject());
        ordDetTable.setItems(orderDetails);
    }
    //region ShowDialogs
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
        addStage.setTitle("Добавление/обновление заказа");
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

    private void showDialog(Service service) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AdminApp.class.getResource("newmodelviews/new-service.fxml"));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Добавление/обновление услуги");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(AdminApp.getPrimaryStage());
        Scene scene =new Scene(page);
        addStage.setScene(scene);
        NewServiceController controller = loader.getController();
        controller.setService(service);
        controller.setStage(addStage);
        addStage.showAndWait();
    }
    private void showDialog(GuardedObject object) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AdminApp.class.getResource("newmodelviews/new-object.fxml"));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Добавление/обновление охраняемого объекта");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(AdminApp.getPrimaryStage());
        Scene scene =new Scene(page);
        addStage.setScene(scene);
        NewObjectController controller = loader.getController();
        controller.setgObject(object);
        controller.setStage(addStage);
        addStage.showAndWait();
    }
    private void showDialog(OrderDetail od) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AdminApp.class.getResource("newmodelviews/new-order-detail.fxml"));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Добавление/обновление подробности заказа");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(AdminApp.getPrimaryStage());
        Scene scene =new Scene(page);
        addStage.setScene(scene);
        NewOrderDetailController controller = loader.getController();
        controller.setOrderDetail(od);
        HashMap<Integer,Order> hashOrd =new HashMap<>();
        for (Order o : orders)
            hashOrd.put(o.getId(), o);
        controller.setOrders(hashOrd);
        HashMap<String,Service> hashServ =new HashMap<>();
        for (Service s : services)
            hashServ.put(s.getName(),s);
        controller.setServices(hashServ);
        HashMap<String,GuardedObject> hashObj =new HashMap<>();
        for (GuardedObject o : guardedObjects)
            hashObj.put(o.getName(),o);
        controller.setObjects(hashObj);
        controller.setStage(addStage);
        addStage.showAndWait();
    }
    //endregion


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

    //region Действия заказов
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
            updateOrders();
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
            updateOrders();
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
            updateOrders();
        }
    }

    public void onOrdInfo(ActionEvent actionEvent) {
    }

    //endregion

    //region Действия услуг
    public void onServCreate(ActionEvent actionEvent) throws IOException {
        Service service = new Service();
        showDialog(service);
        if (service.getName()==""||service.getPrice()==0){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Не все необходимые данные заполнены");
            alert.showAndWait();
        }
        else {
            int rows = servServ.create(service);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Добавление");
            alert.setHeaderText("Данные успешно добавлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateServices();
        }
    }

    public void onServUpdate(ActionEvent actionEvent) throws IOException {
        Service service = servicesTable.getSelectionModel().getSelectedItem();
        if (service!=null){
            showDialog(service);
            int rows = servServ.update(service);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Обновление");
            alert.setHeaderText("Данные успешно Обновлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateServices();
        }
    }

    public void onServDelete(ActionEvent actionEvent) {
        Service service = servicesTable.getSelectionModel().getSelectedItem();
        if (service!=null){
            int rows = servServ.delete(service);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Удаление");
            alert.setHeaderText("Данные успешно удалены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateServices();
        }
    }

    public void onServInfo(ActionEvent actionEvent) {
    }




    //endregion

    //region Действия Объектов
    public void onObjCreate(ActionEvent actionEvent) throws IOException {
        GuardedObject object = new GuardedObject();
        showDialog(object);
        if (object.getName()==""||object.getCity()==""||object.getStreet()==""||object.getBuilding()==""){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Не все необходимые данные заполнены");
            alert.showAndWait();
        }
        else {
            int rows = objServ.create(object);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Добавление");
            alert.setHeaderText("Данные успешно добавлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateObjects();
        }
    }

    public void onObjUpdate(ActionEvent actionEvent) throws IOException {
        GuardedObject object = objectsTable.getSelectionModel().getSelectedItem();
        if (object!=null){
            showDialog(object);
            int rows = objServ.update(object);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Обновление");
            alert.setHeaderText("Данные успешно Обновлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateObjects();
        }
    }
    public void onObjDelete(ActionEvent actionEvent) {
        GuardedObject object = objectsTable.getSelectionModel().getSelectedItem();
        if (object!=null){
            int rows = objServ.delete(object);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Удаление");
            alert.setHeaderText("Данные успешно удалены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateObjects();
        }
    }

    public void onObjInfo(ActionEvent actionEvent){
        GuardedObject object = objectsTable.getSelectionModel().getSelectedItem();
        if (object!=null){
            File file = new File(object.getImage());
            objImgView.setImage(new Image(file.toURI().toString()));
        }
    }
    //endregion
    public void onOdCreate(ActionEvent actionEvent) throws IOException {
        OrderDetail od = new OrderDetail();
        showDialog(od);
        if (od.getOrder()==null||od.getService()==null||od.getGuardedObject()==null){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Не все необходимые данные заполнены");
            alert.showAndWait();
        }
        else {
            int rows = odServ.create(od);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Добавление");
            alert.setHeaderText("Данные успешно добавлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateOrderDetails();
        }
    }

    public void onOdUpdate(ActionEvent actionEvent) throws IOException {
        OrderDetail od = ordDetTable.getSelectionModel().getSelectedItem();
        if (od!=null){
            showDialog(od);
            int rows = odServ.update(od);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Обновление");
            alert.setHeaderText("Данные успешно Обновлены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateOrderDetails();
        }
    }

    public void onOdDelete(ActionEvent actionEvent) {
        OrderDetail od = ordDetTable.getSelectionModel().getSelectedItem();
        if (od!=null){
            int rows = odServ.delete(od);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Удаление");
            alert.setHeaderText("Данные успешно удалены");
            alert.setContentText("Затронуто "+rows+" строк");
            alert.showAndWait();
            updateOrderDetails();
        }
    }



}