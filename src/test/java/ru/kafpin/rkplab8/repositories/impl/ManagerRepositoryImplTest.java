package ru.kafpin.rkplab8.repositories.impl;

import org.junit.jupiter.api.*;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Manager;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ManagerRepositoryImplTest {
    ArrayList<Category> cats=null;
    ArrayList<Manager> mans=null;
    CategoryRepositoryImpl catRep=null;
    ManagerRepositoryImpl manRep=null;
    @BeforeAll
    void setUp(){
        try {
            SQLHelper.connection= DriverManager.getConnection(SQLHelper.DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catRep = new CategoryRepositoryImpl();
        manRep = new ManagerRepositoryImpl();
    }
    @BeforeEach
    void fill(){
        Statement stm = null;
        try {
            stm = SQLHelper.connection.createStatement();
            stm.execute(SQLHelper.CATEGORIES);
            stm.execute(SQLHelper.MANAGERS);
            cats = new ArrayList<>();
            cats.add(new Category("Младший менеджер",25000));
            cats.add(new Category("Старший менеджер",50000));
            cats.add(new Category("Топ менеджер",75000));
            for(var c : cats){
                catRep.save(c);
            }
            cats = new ArrayList<>(catRep.findAll()) ;
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
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
    @AfterEach
    void cleaning(){
        try {
            Statement statement = SQLHelper.connection.createStatement();
            String sql = "DROP TABLE Categories";
            statement.execute(sql);
            sql ="DROP TABLE Managers";
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
        Collection<Manager> actual = manRep.findAll();
        assertEquals(mans,actual);
    }

    @Test
    void findOneById() {
        var finded = manRep.findOneById(1);
        Manager manager =null;
        if (!finded.isEmpty())
            manager = finded.stream().findFirst().orElse(null);
        assertEquals(mans.get(0),manager);
    }

    @Test
    void insert() {
        int rows = manRep.save(new Manager("Полушев","Иван","Максимович","Среднее",
                cats.get(0),cats.get(0).getId(), LocalDate.now(),"867166870158024500000"));
        assertEquals(1,rows);
    }
    @Test
    void update() {
        mans.get(0).setCategory(cats.get(1));
        mans.get(0).setCategoryId(cats.get(1).getId());
        int rows = manRep.save(mans.get(0));
        assertEquals(1,rows);
    }
    @Test
    void delete() {
        int rows = manRep.delete(mans.get(0));
        assertEquals(1,rows);
    }
}