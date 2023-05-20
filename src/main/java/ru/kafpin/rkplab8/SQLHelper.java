package ru.kafpin.rkplab8;

import java.sql.Connection;

public class SQLHelper {
    public final static String DB_URL="jdbc:sqlite:SecurityTest.db";
    public static Connection connection=null;
    //region Создание таблиц
    public final static String MANAGERS="CREATE TABLE \"Managers\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL UNIQUE,\n" +
            "\t\"surname\"\tTEXT,\n" +
            "\t\"name\"\tTEXT NOT NULL,\n" +
            "\t\"patronymic\"\tTEXT,\n" +
            "\t\"education\"\tTEXT NOT NULL,\n" +
            "\t\"categoryId\"\tINTEGER NOT NULL,\n" +
            "\t\"dateOfStart\"\tTEXT NOT NULL,\n" +
            "\t\"accountNumber\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            "\tCONSTRAINT fk_category\n" +
            "\t\tFOREIGN KEY(categoryId)\n" +
            "\t\tREFERENCES Categories(id)\t\n" +
            ");";
    public final static String CATEGORIES = "CREATE TABLE \"Categories\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL UNIQUE,\n" +
            "\t\"name\"\tTEXT NOT NULL,\n" +
            "\t\"salary\"\tREAL NOT NULL,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ");";
    public final static String CLIENTS = "CREATE TABLE \"Clients\" (\n" +
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
    public final static  String ORDERS = "CREATE TABLE \"Orders\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL UNIQUE,\n" +
            "\t\"clientId\"\tINTEGER NOT NULL,\n" +
            "\t\"managerId\"\tINTEGER NOT NULL,\n" +
            "\t\"dateOfSigning\"\tTEXT NOT NULL,\n" +
            "\t\"dateOfComplete\"\tTEXT NOT NULL,\n" +
            "\t\"price\"\tREAL NOT NULL,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT),\n" +
            "\tCONSTRAINT \"fk_managers\" FOREIGN KEY(\"managerId\") REFERENCES \"Managers\"(\"id\"),\n" +
            "\tCONSTRAINT \"fk_client\" FOREIGN KEY(\"clientId\") REFERENCES \"Clients\"(\"id\")\n" +
            ");";
    //endregion

    //region Клиенты
    public final static String CLIENT_SELECT_ALL="SELECT * FROM clients";
    public final static String CLIENT_SELECT_ONE="SELECT * FROM clients where id=?";
    public final static String CLIENT_INSERT="INSERT INTO Clients (surname,name,patronymic,phonenum,typeOfPerson,city,street,building,accountnum)" +
            "values(?,?,?,?,?,?,?,?,?);";
    public final static String CLIENT_UPDATE="update Clients surname=?,name=?,patronymic=?,phonenum=?,typeOfPerson=?,city=?," +
            "street=?,building=?,accountnum=? where id=?";
    public final static String CLIENT_DELETE="delete from Clients where id=?";
    //endregion

    //region Менеджеры
    public final static String MANAGER_SELECT_ALL="SELECT * FROM Managers";
    public final static String MANAGER_SELECT_ONE="SELECT * FROM Managers where id=?";
    public final static String MANAGER_INSERT="INSERT INTO Managers (surname,name,patronymic,education,categoryId,dateOfStart,accountNumber)" +
            "values(?,?,?,?,?,?,?);";
    public final static String MANAGER_UPDATE="update Managers set surname=?,name=?,patronymic=?,education=?,categoryId=?," +
             "dateOfStart=?,accountNumber=?  where id=?";
    public final static String MANAGER_DELETE="delete from Managers where id=?";
    //endregion

    //region Категории
    public final static String CATEGORY_SELECT_ALL="SELECT * FROM Categories";
    public final static String CATEGORY_SELECT_ONE="SELECT * FROM Categories where id=?";
    public final static String CATEGORY_INSERT="insert into Categories(name,salary) values(?,?);";
    public final static String CATEGORY_UPDATE="update Categories set name=?, salary=? where id=?";
    public final static String CATEGORY_DELETE="delete from Categories where Id=?";
    //endregion

    //region Заказы
    public final static String ORDERS_SELECT_ALL="SELECT * FROM Orders";
    public final static String ORDERS_SELECT_ONE="SELECT * FROM Orders where id=?";
    public final static String ORDERS_INSERT="insert into Orders(clientId,managerId,dateOfSigning,dateOfComplete,price) " +
            "values(?,?,?,?,?)";
    public final static String ORDERS_UPDATE="update Orders set clientId=?,managerId=?,dateOfSigning=?,dateOfComplete=?,price=? where id=?";
    public final static String ORDERS_DELETE="delete from Orders where id=?";
    //endregion

}
