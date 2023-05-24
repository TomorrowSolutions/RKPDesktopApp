package ru.kafpin.rkplab8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminApp extends Application {
    private static Stage primaryStage;
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    @Override
    public void start(Stage stage) throws IOException {
        try {
            SQLHelper.connection= DriverManager.getConnection(SQLHelper.DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApp.class.getResource("admin-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Настольное приложение охранной организации");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop() throws Exception {
        SQLHelper.connection.close();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}