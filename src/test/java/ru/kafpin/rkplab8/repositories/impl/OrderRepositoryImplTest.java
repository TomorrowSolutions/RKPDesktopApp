package ru.kafpin.rkplab8.repositories.impl;

import org.junit.jupiter.api.*;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.Manager;
import ru.kafpin.rkplab8.models.Order;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderRepositoryImplTest {
    ArrayList<Manager> mans=null;
    ArrayList<Client> clients=null;
    ArrayList<Order> orders=null;
    ClientRepositoryImpl clRep =null;
    ManagerRepositoryImpl manRep=null;
    OrderRepositoryImpl orderRepository = null;
    @BeforeAll
    void setUp() {
        try {
            SQLHelper.connection= DriverManager.getConnection(SQLHelper.DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        manRep = new ManagerRepositoryImpl();
        clRep = new ClientRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findOneById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}