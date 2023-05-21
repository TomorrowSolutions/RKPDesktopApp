package ru.kafpin.rkplab8.repositories.impl;

import org.junit.jupiter.api.*;
import ru.kafpin.rkplab8.SQLHelper;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.TypeOfPerson;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientRepositoryImplTest {
    ArrayList<Client> clients=null;
    ClientRepositoryImpl clRep=null;
    @BeforeAll
    void setUp() {
        try {
            SQLHelper.connection= DriverManager.getConnection(SQLHelper.DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clRep= new ClientRepositoryImpl();
    }

    @BeforeEach
    void fill(){
        Statement stm= null;
        try {
            stm = SQLHelper.connection.createStatement();
            stm.execute(SQLHelper.CLIENTS);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        Client client1 = new Client("Голяков","Иван","Аркадьевич","88005553535", TypeOfPerson.INDIVIDUAL,
                "Москва","Гвардейская","123","867166870158024500000");
        Client client2 = new Client("Андреева","Ольга","Дмитриева","88007773535", TypeOfPerson.LEGAL,
                "Муром","Московская","11","867166870158065500000");
        Client client3 = new Client("Конева","Валерия","Ивановна","89205553535", TypeOfPerson.INDIVIDUAL,
                "Ульяновск","Петровская","11","867166870158024501337");
        clients = new ArrayList<>();
        clients.add(client1);clients.add(client2);clients.add(client3);
        for (Client c:clients ) {
            clRep.save(c);
        }
        clients = new ArrayList<>(clRep.findAll());
    }
    @AfterEach
    void tearDown() {
        try {
            Statement statement = SQLHelper.connection.createStatement();
            String sql = "DROP TABLE Clients";
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
        Collection<Client> actual = clRep.findAll();
        assertEquals(clients,actual);
    }
    @Test
    void findOneById(){
        var finded = clRep.findOneById(1);
        Client client= null;
        if (!finded.isEmpty())
            client=finded.stream().findFirst().orElse(null);
        assertEquals(clients.get(0),client);
    }
    @Test
    void insert(){
        int actual =clRep.save(new Client("Щебляков","Григорий","Миронович","88005553535", TypeOfPerson.INDIVIDUAL,
                "Москва","Гвардейская","123","867166870158024500000"));
        assertEquals(1,actual);
    }
    @Test
    void update(){
        clients.get(0).setSurname("Миронов");
        int actual =clRep.save(clients.get(0));
        assertEquals(1,actual);
    }
    @Test
    void delete(){
        int actual = clRep.delete(clients.get(0));
        assertEquals(1,actual);
    }
}