package ru.kafpin.rkplab8.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import ru.kafpin.rkplab8.models.GuardedObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

public class NewObjectController {
    public TextField nameTF;
    public TextField imgTF;
    public TextField cityTF;
    public TextField streetTF;
    public TextField buildingTF;
    private Stage stage;
    private GuardedObject gObject;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setgObject(GuardedObject gObject) {
        this.gObject = gObject;
        nameTF.setText(gObject.getName());
        imgTF.setText(gObject.getImage());
        cityTF.setText(gObject.getCity());
        streetTF.setText(gObject.getStreet());
        buildingTF.setText(gObject.getBuilding());
    }

    public void onOpenImg(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.setTitle("Open image file");
        FileChooser.ExtensionFilter filter1 =
                new FileChooser.ExtensionFilter("All image files", "*.png", "*.jpg", "*.gif","*.tif");
        FileChooser.ExtensionFilter filter2 =
                new FileChooser.ExtensionFilter("JPEG files", "*.jpg");
        FileChooser.ExtensionFilter filter3 =
                new FileChooser.ExtensionFilter("PNG image Files", "*.png");
        FileChooser.ExtensionFilter filter4 =
                new FileChooser.ExtensionFilter("GIF image Files", "*.gif");
        FileChooser.ExtensionFilter filter5 =
                new FileChooser.ExtensionFilter("TIF image Files", "*.tif");
        fileChooser.getExtensionFilters().addAll(filter1, filter2, filter3, filter4, filter5);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            imgTF.setText(file.getCanonicalPath());
        }
    }
    private String generateString() {
        String s = UUID.randomUUID().toString().replace("-","");
        return s.substring(0,Math.min(s.length(),10));
    }
    public void onOk(ActionEvent actionEvent) {
        gObject.setName(nameTF.getText());
        if (imgTF.getText()!=""){
            File src= new File(imgTF.getText());
            String ext = FilenameUtils.getExtension(src.getName());
            File dst = new File("imgs/"+generateString()+"."+ext);
            try {
                FileUtils.copyFile(src,dst);
                gObject.setImage(dst.getPath());
            } catch (IOException e) {
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Ошибка загрузки");
                alert.setContentText("Не удалось загрузить изображение");
                alert.showAndWait();
            }
        }
        else gObject.setImage("");
        gObject.setCity(cityTF.getText());
        gObject.setStreet(streetTF.getText());
        gObject.setBuilding(buildingTF.getText());
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent) {
        stage.close();
    }
}
