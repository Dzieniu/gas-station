package com.dzieniu2.controller;

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
    void toGasTanks(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/GasTanks.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    void toOne(MouseEvent event) throws IOException {
        BorderPane two = FXMLLoader.load(getClass().getResource("/fxml/One.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    void toThree(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/Three.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    void toTwo(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/Two.fxml"));
        mainBorderPane.setCenter(two);
    }
}
