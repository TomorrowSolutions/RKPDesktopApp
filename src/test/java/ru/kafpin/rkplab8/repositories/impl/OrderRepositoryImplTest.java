package ru.kafpin.rkplab8.repositories.impl;

import org.junit.jupiter.api.*;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.*;
import ru.kafpin.rkplab8.models.Order;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderRepositoryImplTest {
    ArrayList<Manager> mans=null;
    ArrayList<Client> clients=null;
    ArrayList<Order> orders=null;
    ArrayList<Category> cats=null;
    ClientRepositoryImpl clRep =null;
    ManagerRepositoryImpl manRep=null;
    OrderRepositoryImpl ordRep = null;
    CategoryRepositoryImpl catRep = null;
    @BeforeAll
    void setUp() {
        try {
            SQLHelper.connection= DriverManager.getConnection(SQLHelper.DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        manRep = new ManagerRepositoryImpl();
        clRep = new ClientRepositoryImpl();
        ordRep = new OrderRepositoryImpl();
        catRep = new CategoryRepositoryImpl();
    }

    @BeforeEach
    void filling(){
        Statement stm = null;
        try {
            stm = SQLHelper.connection.createStatement();
            stm.execute(SQLHelper.CATEGORIES);
            stm.execute(SQLHelper.MANAGERS);
            stm.execute(SQLHelper.CLIENTS);
            stm.execute(SQLHelper.ORDERS);
            cats = new ArrayList<>();
            cats.add(new Category("Младший менеджер",25000));
            cats.add(new Category("Старший менеджер",50000));
            cats.add(new Category("Топ менеджер",75000));
            for(var c : cats){
                catRep.save(c);
            }
            cats = new ArrayList<>(catRep.findAll());
            mans = new ArrayList<>();
            mans.add(new Manager("Голяков","Иван","Аркадьевич","Среднее",
                    cats.get(0),cats.get(0).getId(), LocalDate.now(),"867166870158024500000"));
            mans.add(new Manager("Кобзева","Алиса","Сергеевна","Среднее",
                    cats.get(1),cats.get(1).getId(), LocalDate.now(),"867166870158024500000"));
            mans.add(new Manager("Миронов","Иван","Львович","Среднее",
                    cats.get(2),cats.get(2).getId(), LocalDate.now(),"867166870158024500000"));
            for (var m : mans){
                manRep.save(m);
            }
            mans = new ArrayList<>(manRep.findAll());
            clients = new ArrayList<>();
            clients.add( new Client("Голяков","Иван","Аркадьевич","88005553535", TypeOfPerson.INDIVIDUAL,
                    "Москва","Гвардейская","123","867166870158024500000"));
            clients.add(new Client("Андреева","Ольга","Дмитриева","88007773535", TypeOfPerson.LEGAL,
                    "Муром","Московская","11","867166870158065500000"));
            clients.add( new Client("Конева","Валерия","Ивановна","89205553535", TypeOfPerson.INDIVIDUAL,
                    "Ульяновск","Петровская","11","867166870158024501337"));
            for (Client c:clients ) {
                clRep.save(c);
            }
            clients = new ArrayList<>(clRep.findAll());
            orders = new ArrayList<>();
            orders.add(new Order(clients.get(0),clients.get(0).getId(),mans.get(0),mans.get(0).getId(),
                    LocalDate.now(),LocalDate.now().plusDays(1),50000));
            orders.add(new Order(clients.get(1),clients.get(1).getId(),mans.get(1),mans.get(1).getId(),
                    LocalDate.now(),LocalDate.now().plusDays(1),75000));
            orders.add(new Order(clients.get(2),clients.get(2).getId(),mans.get(2),mans.get(2).getId(),
                    LocalDate.now(),LocalDate.now().plusDays(1),100000));
            for(Order o : orders)
                ordRep.save(o);
            orders = new ArrayList<>(ordRep.findAll());

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @AfterEach
    void cleaning() {
        try {
            Statement statement = SQLHelper.connection.createStatement();
            String sql = "DROP TABLE Categories";
            statement.execute(sql);
            sql ="DROP TABLE Managers";
            statement.execute(sql);
            sql ="DROP TABLE Clients";
            statement.execute(sql);
            sql ="DROP TABLE Orders";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    @AfterAll
    void killDb(){
        try {
            SQLHelper.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        File file = new File("SecurityTest.db");
        file.delete();
    }

    @Test
    void findAll() {
        Collection<Order> actual = ordRep.findAll();
        assertEquals(orders,actual);
    }

    @Test
    void findOneById() {
        var finded  = ordRep.findOneById(orders.get(0).getId());
        Order order = null;
        if (!finded.isEmpty())
            order = finded.stream().findFirst().orElse(null);
        assertEquals(orders.get(0),order);
    }

    @Test
    void insert() {
        int actual =  ordRep.save(new Order(clients.get(1),clients.get(1).getId(),mans.get(2),mans.get(2).getId(),
                LocalDate.now(),LocalDate.now().plusDays(1),50000));
        assertEquals(1,actual);
    }
    @Test
    void update() {
        orders.get(0).setClient(clients.get(1));
        orders.get(0).setClientId(clients.get(1).getId());
        int actual =  ordRep.save(orders.get(0));
        assertEquals(1,actual);
    }
    @Test
    void delete() {
        int actual= ordRep.delete(orders.get(0));
        assertEquals(1,actual);
    }
}