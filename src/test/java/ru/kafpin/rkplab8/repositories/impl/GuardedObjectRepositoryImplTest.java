package ru.kafpin.rkplab8.repositories.impl;

import org.junit.jupiter.api.*;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.GuardedObject;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GuardedObjectRepositoryImplTest {
    ArrayList<GuardedObject> gObjects = null;
    GuardedObjectRepositoryImpl gObjRep = null;
    @BeforeAll
    void setUp() {
        try {
            SQLHelper.connection= DriverManager.getConnection(SQLHelper.DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        gObjRep=new GuardedObjectRepositoryImpl();
    }
    @BeforeEach
    void fill() {
        Statement stm= null;
        try {
            stm = SQLHelper.connection.createStatement();
            stm.execute(SQLHelper.GUARDEDOBJECTS);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        gObjects=new ArrayList<>();
        gObjects.add(new GuardedObject("Склад","example","Москва","Прачечная","123"));
        gObjects.add(new GuardedObject("Офис","example","Мурм","Московская","123"));
        gObjects.add(new GuardedObject("Завод","example","Орел","Первомайская","123"));
        for (GuardedObject g : gObjects){
            gObjRep.save(g);
        }
        gObjects = new ArrayList<>(gObjRep.findAll());
    }
    @AfterEach
    void cleaning() {
        try {
            Statement statement = SQLHelper.connection.createStatement();
            String sql = "DROP TABLE GuardedObjects";
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
        Collection<GuardedObject> actual =gObjRep.findAll();
        assertEquals(gObjects,actual);
    }

    @Test
    void findOneById() {
        var finded = gObjRep.findOneById(gObjects.get(0).getId());
        GuardedObject actual = null;
        if (!finded.isEmpty())
            actual=finded.stream().findFirst().orElse(null);
        assertEquals(gObjects.get(0),actual);
    }

    @Test
    void insert() {
        int actual  = gObjRep.save(new GuardedObject("Магазин","example","Майкоп","Тестовая","123"));
        assertEquals(1,actual);

    }
    @Test
    void update() {
        gObjects.get(0).setName("Изменено");
        int actual  = gObjRep.save(gObjects.get(0));
        assertEquals(1,actual);
    }
    @Test
    void delete() {
        int actual = gObjRep.delete(gObjects.get(0));
        assertEquals(1,actual);
    }
}