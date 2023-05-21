package ru.kafpin.rkplab8.repositories.impl;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.*;
import ru.kafpin.rkplab8.repositories.inter.OrderDetailRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {

    OrderRepositoryImpl ordRep;
    GuardedObjectRepositoryImpl objRep;
    ServiceRepositoryImpl servRep;

    Alert alert;

    public OrderDetailRepositoryImpl() {
        this.ordRep = new OrderRepositoryImpl();
        this.objRep = new GuardedObjectRepositoryImpl();
        this.servRep = new ServiceRepositoryImpl();
        this.alert = null;
    }

    @Override
    public Collection<OrderDetail> findAll() {
        Collection<OrderDetail> orderDetails = null;
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
            ResultSet resultSet = statement.executeQuery(SQLHelper.ORDERDETAIL_SELECT_ALL);
            orderDetails = mapper(resultSet);
        } catch (SQLException e) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка выполнения запроса");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            Platform.exit();
        }
        return orderDetails;
    }

    @Override
    public Optional<OrderDetail> findOneById(int id) {
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.ORDERDETAIL_SELECT_ONE);
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            OrderDetail orderDetail = null;
            while (rs.next()){
                int orderId = rs.getInt("orderId");
                var ord = ordRep.findOneById(orderId);
                Order order = ord.isEmpty() ? null : ord.stream().findFirst().orElse(null);
                int objectId = rs.getInt("objectId");
                var obj = objRep.findOneById(objectId);
                GuardedObject gObject = obj.isEmpty() ? null : obj.stream().findFirst().orElse(null);
                int serviceId = rs.getInt("serviceId");
                var serv = servRep.findOneById(serviceId);
                int quantity = rs.getInt("quantity");
                Service service = serv.isEmpty() ? null : serv.stream().findFirst().orElse(null);
                orderDetail = new OrderDetail(id,order,orderId,gObject,objectId,service,serviceId,quantity);
            }
            if (orderDetail!=null)
                return Optional.of(orderDetail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    private PreparedStatement InsertOrUpdate(OrderDetail orderDetail) throws SQLException {
        PreparedStatement pstm = null;
        if (orderDetail == null) {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка создания сессии");
            alert.setContentText("Переданный клиент является пустым");
            alert.showAndWait();
        }
        else {
            if (orderDetail.getId()==0){
                pstm = SQLHelper.connection.prepareStatement(SQLHelper.ORDERDETAIL_INSERT);
                pstm.setInt(1, orderDetail.getOrderId());
                pstm.setInt(2, orderDetail.getGObjectId());
                pstm.setInt(3, orderDetail.getServiceId());
                pstm.setInt(4, orderDetail.getQuantity());
            }
            else {
                pstm = SQLHelper.connection.prepareStatement(SQLHelper.ORDERDETAIL_UPDATE);
                pstm.setInt(1, orderDetail.getOrderId());
                pstm.setInt(2, orderDetail.getGObjectId());
                pstm.setInt(3, orderDetail.getServiceId());
                pstm.setInt(4, orderDetail.getQuantity());
                pstm.setInt(5, orderDetail.getId());
            }
        }
        return pstm;
    }
    @Override
    public int save(OrderDetail orderDetail) {
        PreparedStatement statement = null;
        int rows=0;
        try {
            statement = InsertOrUpdate(orderDetail);
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
    public int delete(OrderDetail orderDetail) {
        int rows = 0;
        try {
            PreparedStatement statement = SQLHelper.connection.prepareStatement(SQLHelper.ORDERDETAIL_DELETE);
            statement.setInt(1,orderDetail.getId());
            rows=statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
    private Collection<OrderDetail> mapper(ResultSet resultSet) throws SQLException {
        Collection<OrderDetail> orderDetails = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("Id");
            int orderId = resultSet.getInt("orderId");
            var ord = ordRep.findOneById(orderId);
            Order order = ord.isEmpty() ? null : ord.stream().findFirst().orElse(null);
            int objectId = resultSet.getInt("objectId");
            var obj = objRep.findOneById(objectId);
            GuardedObject gObject = obj.isEmpty() ? null : obj.stream().findFirst().orElse(null);
            int serviceId = resultSet.getInt("serviceId");
            var serv = servRep.findOneById(serviceId);
            int quantity = resultSet.getInt("quantity");
            Service service = serv.isEmpty() ? null : serv.stream().findFirst().orElse(null);
            orderDetails.add(new OrderDetail(id,order,orderId,gObject,objectId,service,serviceId,quantity));
        }
        return orderDetails;
    }
}
