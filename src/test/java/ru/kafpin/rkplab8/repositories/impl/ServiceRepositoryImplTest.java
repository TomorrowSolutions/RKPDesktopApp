package ru.kafpin.rkplab8.repositories.impl;

import org.junit.jupiter.api.*;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.GuardedObject;
import ru.kafpin.rkplab8.models.Service;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceRepositoryImplTest {
    ArrayList<Service> services = null;
    ServiceRepositoryImpl servRep =null;
    @BeforeAll
    void setUp() {
        try {
            SQLHelper.connection= DriverManager.getConnection(SQLHelper.DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        servRep=new ServiceRepositoryImpl();
    }
    @BeforeEach
    void filling(){
        Statement stm= null;
        try {
            stm = SQLHelper.connection.createStatement();
            stm.execute(SQLHelper.SERVICES);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        services=new ArrayList<>();
        services.add(new Service("Охрана",100500d,1));
        services.add(new Service("Сопровождение",100531d,1));
        services.add(new Service("Тест",120500d,1));
        for (Service s : services){
            servRep.save(s);
        }
        services = new ArrayList<>(servRep.findAll());
    }
    @AfterEach
    void cleaning() {
        try {
            Statement statement = SQLHelper.connection.createStatement();
            String sql = "DROP TABLE Services";
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
        Collection<Service> actual = servRep.findAll();
        assertEquals(services,actual);
    }

    @Test
    void findOneById() {
        var finded = servRep.findOneById(services.get(0).getId());
        Service service = null;
        if (!finded.isEmpty())
            service=finded.stream().findFirst().orElse(null);
        assertEquals(services.get(0),service);
    }

    @Test
    void insert() {
        int actual  = servRep.save(new Service("Testing",100500d,1));
        assertEquals(1,actual);

    }
    @Test
    void update() {
        services.get(0).setName("Изменено");
        int actual  = servRep.save(services.get(0));
        assertEquals(1,actual);
    }
    @Test
    void delete() {
        int actual = servRep.delete(services.get(0));
        assertEquals(1,actual);
    }
}