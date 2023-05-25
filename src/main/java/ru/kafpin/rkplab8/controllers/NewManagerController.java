package ru.kafpin.rkplab8.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Manager;

import java.util.Map;

public class NewManagerController {

    public TextField surnTF;
    public TextField nameTF;
    public TextField patrTF;
    public TextField eduTF;
    public ComboBox<String> categoryCombo;
    public TextField accnumTF;
    public DatePicker datePicker;

    private Stage stage;
    private Map<String, Category> categories;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCategories(Map<String, Category> categories) {
        this.categories = categories;
        //Добавить в комбо
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    private Manager manager;
    public void onOk(ActionEvent actionEvent) {
    }

    public void onCancel(ActionEvent actionEvent) {
    }
}
