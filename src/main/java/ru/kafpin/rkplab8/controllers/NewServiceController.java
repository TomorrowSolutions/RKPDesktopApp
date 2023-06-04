package ru.kafpin.rkplab8.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.kafpin.rkplab8.models.Service;

public class NewServiceController {

    public TextField nameTF;
    public TextField periodTF;
    public TextField priceTF;

    private Stage stage;
    private Service service;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setService(Service service) {
        this.service = service;
        nameTF.setText(service.getName());
        periodTF.setText(String.valueOf(service.getPeriodOfExecution()));
        priceTF.setText(String.valueOf(service.getPrice()));
    }

    public void onOk(ActionEvent actionEvent) {
        service.setName(nameTF.getText());
        try{
            service.setPeriodOfExecution(Integer.parseInt(periodTF.getText()));
            service.setPrice(Double.parseDouble(periodTF.getText()));
        }catch (Exception e){
            service.setPrice(0d);
            service.setPeriodOfExecution(0);
        }
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent) {stage.close();}
}
