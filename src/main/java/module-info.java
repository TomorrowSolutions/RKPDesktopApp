module ru.kafpin.rkplab8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.apache.commons.io;
    requires java.desktop;


    opens ru.kafpin.rkplab8 to javafx.fxml;
    exports ru.kafpin.rkplab8;
    exports ru.kafpin.rkplab8.controllers;
    opens ru.kafpin.rkplab8.controllers to javafx.fxml;
}