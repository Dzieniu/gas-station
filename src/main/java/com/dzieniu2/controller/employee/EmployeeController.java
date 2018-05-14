package com.dzieniu2.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class EmployeeController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private BorderPane cameraPane;

    @FXML
    public void initialize() throws IOException {
        loadCamera();
    }

    @FXML
    void toGasTanks(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/GasTanks.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    void toOne(MouseEvent event) throws IOException {
        BorderPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/ProductList.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    void toThree(MouseEvent event) throws IOException {
        BorderPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/AddClient.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    void toTwo(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/Two.fxml"));
        mainBorderPane.setCenter(two);
    }

    private void loadCamera() throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/fxml/employee/CameraPane.fxml"));
        cameraPane.setCenter(pane);
    }

    @FXML
    void Station2BtnClick(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/fxml/employee/Station.fxml"));
        mainBorderPane.setCenter(pane);
    }


    @FXML
    void Station3BtnCllick(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/fxml/employee/Station.fxml"));
        mainBorderPane.setCenter(pane);
    }

    @FXML
    void station1BtnClick(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/fxml/employee/Station.fxml"));
        mainBorderPane.setCenter(pane);
    }
}
