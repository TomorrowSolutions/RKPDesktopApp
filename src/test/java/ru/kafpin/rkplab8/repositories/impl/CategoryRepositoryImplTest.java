package ru.kafpin.rkplab8.repositories.impl;

import org.junit.jupiter.api.*;
import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.TypeOfPerson;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryRepositoryImplTest {
    Connection testConn =null;
    ArrayList<Category> list=null;
    CategoryRepositoryImpl repos=null;
    @BeforeAll
    void setUp() {
        String url = "jdbc:sqlite:SecurityTest.db";
        try (Connection conn = DriverManager.getConnection(url)){
            if (conn!=null){
                testConn=DriverManager.getConnection(url);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        repos= new CategoryRepositoryImpl();
    }
    @BeforeEach
    void fill() {
        String sql ="CREATE TABLE \"Categories\" (\n" +
                "\"id\" INTEGER NOT NULL UNIQUE,\n" +
                "\"name\" TEXT NOT NULL,\n" +
                "\"salary\" REAL NOT NULL,\n" +
                "PRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");";
        Statement stm= null;
        try {
            stm = testConn.createStatement();
            stm.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        list=new ArrayList<>();
        list.add(new Category(1,"Младший менеджер",25000));
        list.add(new Category(2,"Старший менеджер",50000));
        list.add(new Category(3,"Топ менеджер",75000));
        sql ="insert into Categories(name,salary) values(?,?);";
        for (Category c : list){
            try {
                PreparedStatement pstm = testConn.prepareStatement(sql);
                pstm.setString(1,c.getName());
                pstm.setDouble(2,c.getSalary());
                pstm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Statement statement = testConn.createStatement();
            String sql = "DROP TABLE Categories";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    void findAll() {
        Collection<Category> actual = repos.findAll();
        assertEquals(list,actual);
    }

    @Test
    void findOneById() {
        var finded = repos.findOneById(1);
        Category category= null;
        if (!finded.isEmpty())
            category=finded.stream().findFirst().orElse(null);
        assertEquals(list.get(0),category);
    }

    @Test
    void insert() {
        int actual =repos.save(new Category("Менеджер по продажам",35000));
        assertEquals(1,actual);
    }
    @Test
    void update() {
        list.get(0).setName("тест");
        int actual =repos.save(list.get(0));
        assertEquals(1,actual);
    }
    @Test
    void updateNonExistent() {
        int actual =repos.save(new Category(13,"Менеджер по продажам",35000));
        assertEquals(1,actual);
    }

    @Test
    void delete() {
        int actual = repos.delete(list.get(0));
        assertEquals(1,actual);
    }
}