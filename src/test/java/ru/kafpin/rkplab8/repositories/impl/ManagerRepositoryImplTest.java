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
            cats.add(new Category(1,"Младший менеджер",25000));
            cats.add(new Category(2,"Старший менеджер",50000));
            cats.add(new Category(3,"Топ менеджер",75000));
            for(var c : cats){
                PreparedStatement pstm = SQLHelper.connection.prepareStatement(SQLHelper.CATEGORY_INSERT);
                pstm.setString(1,c.getName());
                pstm.setDouble(2,c.getSalary());
                pstm.executeUpdate();
            }
            mans = new ArrayList<>();
            mans.add(new Manager(1,"Голяков","Иван","Аркадьевич","Среднее",
                    cats.get(0),1, LocalDate.now(),"867166870158024500000"));
            mans.add(new Manager(2,"Кобзева","Алиса","Сергеевна","Среднее",
                    cats.get(1),2, LocalDate.now(),"867166870158024500000"));
            mans.add(new Manager(3,"Миронов","Иван","Львович","Среднее",
                    cats.get(2),3, LocalDate.now(),"867166870158024500000"));
            for (var m : mans){
                PreparedStatement pstm = SQLHelper.connection.prepareStatement(SQLHelper.MANAGER_INSERT);
                pstm.setString(1,m.getSurname());
                pstm.setString(2,m.getName());
                pstm.setString(3,m.getPatronymic());
                pstm.setString(4,m.getEducation());
                pstm.setInt(5,m.getCategoryId());
                pstm.setString(6,m.getDateOfStart().toString());
                pstm.setString(7,m.getAccountNumber());
                pstm.executeUpdate();
            }
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
                cats.get(0),1, LocalDate.now(),"867166870158024500000"));
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