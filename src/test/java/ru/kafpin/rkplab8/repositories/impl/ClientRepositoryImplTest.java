package ru.kafpin.rkplab8.repositories.impl;

import org.junit.jupiter.api.*;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.TypeOfPerson;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientRepositoryImplTest {
    Connection testConn =null;
    ArrayList<Client> list=null;
    ClientRepositoryImpl repos=null;
    @BeforeAll
    void setUp() {
        String url = "jdbc:sqlite:SecurityTest.db";
        try (Connection conn = DriverManager.getConnection(url)){
            if (conn!=null){
                testConn=DriverManager.getConnection(url);
                /*DatabaseMetaData meta = conn.getMetaData();
                meta.getDriverName();*/
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        repos= new ClientRepositoryImpl();
    }

    @BeforeEach
    void fill(){
        String sql = "CREATE TABLE \"Clients\" (\n" +
                "\"id\" INTEGER NOT NULL UNIQUE,\n" +
                "\"surname\" TEXT NOT NULL,\n" +
                "\"name\" TEXT NOT NULL,\n" +
                "\"patronymic\" TEXT NOT NULL,\n" +
                "\"phonenum\" TEXT NOT NULL,\n" +
                "\"typeOfPerson\" TEXT NOT NULL,\n" +
                "\"city\" TEXT NOT NULL,\n" +
                "\"street\" TEXT NOT NULL,\n" +
                "\"building\" TEXT NOT NULL,\n" +
                "\"accountnum\" TEXT NOT NULL,\n" +
                "PRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");";
        Statement stm= null;
        try {
            stm = testConn.createStatement();
            stm.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        Client client1 = new Client(1,"Голяков","Иван","Аркадьевич","88005553535", TypeOfPerson.INDIVIDUAL,
                "Москва","Гвардейская","123","867166870158024500000");
        Client client2 = new Client(2,"Андреева","Ольга","Дмитриева","88007773535", TypeOfPerson.LEGAL,
                "Муром","Московская","11","867166870158065500000");
        Client client3 = new Client(3,"Конева","Валерия","Ивановна","89205553535", TypeOfPerson.INDIVIDUAL,
                "Ульяновск","Петровская","11","867166870158024501337");
        list = new ArrayList<>();
        list.add(client1);list.add(client2);list.add(client3);
        sql = "insert into Clients(surname,name,patronymic,phonenum,typeOfPerson,city,street,building,accountnum)\n" +
                "values(?,?,?,?,?,?,?,?,?);";
        for (Client c:list ) {
            try {
                PreparedStatement statement = testConn.prepareStatement(sql);
                statement.setString(1,c.getSurname());
                statement.setString(2,c.getName());
                statement.setString(3,c.getPatronymic());
                statement.setString(4,c.getPhoneNum());
                statement.setString(5,c.getTypeOfPerson().toString());
                statement.setString(6,c.getCity());
                statement.setString(7,c.getStreet());
                statement.setString(8,c.getBuilding());
                statement.setString(9,c.getAccountnumber());
                statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
    @AfterEach
    void tearDown() {
        try {
            Statement statement = testConn.createStatement();
            String sql = "DROP TABLE Clients";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    void findAll() {
        Collection<Client> actual = repos.findAll();
        assertEquals(list,actual);
    }
    @Test
    void findOneById(){
        var finded = repos.findOneById(1);
        Client client= null;
        if (!finded.isEmpty())
            client=finded.stream().findFirst().orElse(null);
        assertEquals(list.get(0),client);
    }
    @Test
    void insert(){
        int actual =repos.save(new Client("Щебляков","Григорий","Миронович","88005553535", TypeOfPerson.INDIVIDUAL,
                "Москва","Гвардейская","123","867166870158024500000"));
        assertEquals(1,actual);
    }
    @Test
    void updateNonExistent(){
        list.get(0).setName("Виталий");
        int actual = repos.save(list.get(0));
        assertEquals(1,actual);
    }
    @Test
    void deleteNonExistent(){
        int actual = repos.delete(new Client(123,"Щебляков","Григорий","Миронович","88005553535", TypeOfPerson.INDIVIDUAL,
                "Москва","Гвардейская","123","867166870158024500000"));
        assertEquals(1,actual);
    }
    @Test
    void delete(){
        int actual = repos.delete(list.get(0));
        assertEquals(1,actual);
    }
}