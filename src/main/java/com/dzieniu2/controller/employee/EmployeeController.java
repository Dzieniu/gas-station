package com.dzieniu2.controller.employee;

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
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/Three.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    void toTwo(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/Two.fxml"));
        mainBorderPane.setCenter(two);
    }
}
