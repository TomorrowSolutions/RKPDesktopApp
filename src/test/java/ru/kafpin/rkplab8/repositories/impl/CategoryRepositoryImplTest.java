package ru.kafpin.rkplab8.repositories.impl;

import org.junit.jupiter.api.*;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.Category;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryRepositoryImplTest {
    ArrayList<Category> categories=null;
    CategoryRepositoryImpl catRep=null;
    @BeforeAll
    void setUp() {
        try {
            SQLHelper.connection= DriverManager.getConnection(SQLHelper.DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catRep= new CategoryRepositoryImpl();
    }
    @BeforeEach
    void fill() {
        Statement stm= null;
        try {
            stm = SQLHelper.connection.createStatement();
            stm.execute(SQLHelper.CATEGORIES);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        categories=new ArrayList<>();
        categories.add(new Category("Младший менеджер",25000));
        categories.add(new Category("Старший менеджер",50000));
        categories.add(new Category("Топ менеджер",75000));
        for (Category c : categories){
            catRep.save(c);
        }
        categories = new ArrayList<>(catRep.findAll());
    }

    @AfterEach
    void cleaning() {
        try {
            Statement statement = SQLHelper.connection.createStatement();
            String sql = "DROP TABLE Categories";
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
        Collection<Category> actual = catRep.findAll();
        assertEquals(categories,actual);
    }

    @Test
    void findOneById() {
        var finded = catRep.findOneById(1);
        Category category= null;
        if (!finded.isEmpty())
            category=finded.stream().findFirst().orElse(null);
        assertEquals(categories.get(0),category);
    }

    @Test
    void insert() {
        int actual =catRep.save(new Category("Менеджер по продажам",35000));
        assertEquals(1,actual);
    }
    @Test
    void update() {
        categories.get(0).setName("тест");
        int actual =catRep.save(categories.get(0));
        assertEquals(1,actual);
    }

    @Test
    void delete() {
        int actual = catRep.delete(categories.get(0));
        assertEquals(1,actual);
    }
}