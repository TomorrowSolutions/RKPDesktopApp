package ru.kafpin.rkplab8.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.kafpin.rkplab8.models.Client;
import ru.kafpin.rkplab8.models.TypeOfPerson;

public class NewClientController {
    public TextField surnTF;
    public TextField nameTF;
    public TextField patrTF;
    public TextField phoneTF;
    public RadioButton rIndividual;
    public RadioButton rLegal;
    public TextField cityTF;
    public TextField streetTF;
    public TextField buildingTF;
    public TextField accnumTF;
    private Stage dialogStage;
    private Client client;

    public void setDialogStage(Stage addStage) {
        this.dialogStage = addStage;
    }

    public void setClient(Client client) {
        this.client = client;
        surnTF.setText(client.getSurname());
        nameTF.setText(client.getSurname());
        patrTF.setText(client.getSurname());
        phoneTF.setText(client.getSurname());
        if (client.getTypeOfPerson() == TypeOfPerson.LEGAL) {
            rLegal.setSelected(true);
        } else {
            rIndividual.setSelected(true);
        }
        cityTF.setText(client.getCity());
        streetTF.setText(client.getStreet());
        buildingTF.setText(client.getBuilding());
        accnumTF.setText(client.getAccountnumber());
    }

    public void onOk(ActionEvent actionEvent) {
        client.setSurname(surnTF.getText());
        client.setName(nameTF.getText());
        client.setPatronymic(patrTF.getText());
        client.setPhoneNum(phoneTF.getText());
        if (rLegal.isSelected()) {
            client.setTypeOfPerson(TypeOfPerson.LEGAL);
        } else {
            client.setTypeOfPerson((TypeOfPerson.INDIVIDUAL));
        }
        client.setCity(cityTF.getText());
        client.setStreet(streetTF.getText());
        client.setBuilding(buildingTF.getText());
        client.setAccountnumber(accnumTF.getText());
        dialogStage.close();
    }

    public void onCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }
}
