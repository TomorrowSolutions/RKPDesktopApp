package ru.kafpin.rkplab8.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.kafpin.rkplab8.models.Category;
import ru.kafpin.rkplab8.models.Manager;

import java.time.LocalDate;
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
    private Manager manager;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCategories(Map<String, Category> categories) {
        this.categories = categories;
        categoryCombo.setItems(FXCollections.observableArrayList(categories.keySet()));
        categoryCombo.getSelectionModel().select(0);
    }

    public void setManager(Manager manager) {
        this.manager = manager;
        surnTF.setText(manager.getSurname());
        nameTF.setText(manager.getSurname());
        patrTF.setText(manager.getSurname());
        eduTF.setText(manager.getEducation());
        if (manager.getCategory()!=null) categoryCombo.getSelectionModel().select(manager.getCategory().getName());
        if (manager.getDateOfStart() == null) datePicker.setValue(LocalDate.now());
        else datePicker.setValue(manager.getDateOfStart());
        accnumTF.setText(manager.getAccountNumber());
    }


    public void onOk(ActionEvent actionEvent) {
        manager.setSurname(surnTF.getText());
        manager.setName(nameTF.getText());
        manager.setPatronymic(patrTF.getText());
        manager.setEducation(eduTF.getText());
        var category=categories.get(categoryCombo.getValue());
        manager.setCategory(category);
        manager.setCategoryId(category.getId());
        manager.setDateOfStart(datePicker.getValue());
        manager.setAccountNumber(accnumTF.getText());
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent) {
        stage.close();
    }
}
