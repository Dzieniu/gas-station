package com.dzieniu2.controller.employee;

import com.dzieniu2.other.CameraList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class CameraController {

    @FXML
    private ImageView cameraPane;

    @FXML
    private Label cameraInfoLabel;

    private String cameraDirectory;

    private CameraList cameraList;

    @FXML
    public void initialize(){
        cameraDirectory = "/src/main/resources/camera"; // podajemy ścieżkę do folderu z gifami
        cameraDirectory = System.getProperty("user.dir") + cameraDirectory;
        loadCameras();
        nextCamera();
    }

    public void loadCameras(){

        File directory = new File(cameraDirectory);

        ArrayList<Image> list = new ArrayList<>();
        Arrays.asList(directory.listFiles()).forEach(x -> list.add(new Image(new File(cameraDirectory).getName()+"/"+x.getName(),
                400,250,false,true)));
        cameraList = new CameraList(list);
    }

    private void showCamera(Image image){
        cameraInfoLabel.setText("Cam: "+(cameraList.getSelected()+1));
        cameraPane.setImage(image);
    }

    @FXML
    public void nextCamera(){
        showCamera(cameraList.next());
    }

    @FXML
    public void previousCamera(){
        showCamera(cameraList.previous());
    }
}
