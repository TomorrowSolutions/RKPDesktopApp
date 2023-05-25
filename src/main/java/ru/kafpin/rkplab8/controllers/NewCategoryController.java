package ru.kafpin.rkplab8.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.kafpin.rkplab8.models.Category;

public class NewCategoryController {
    public TextField nameTF;
    public TextField salaryTF;

    private Stage stage;
    private Category category;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCategory(Category category) {
        this.category = category;
        nameTF.setText(category.getName());
        salaryTF.setText(String.valueOf(category.getSalary()));
    }

    public void onOk(ActionEvent actionEvent) {
        category.setName(nameTF.getText());
        try{
           category.setSalary(Double.parseDouble(salaryTF.getText()));
        } catch (Exception e){
            category.setSalary(0);
        }
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent) {
        stage.close();
    }
}
