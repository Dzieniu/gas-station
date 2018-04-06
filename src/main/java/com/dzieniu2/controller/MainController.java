package com.dzieniu2.controller;

import com.dzieniu2.entity.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane MainBorderPane;

    @FXML
    private Label statusBar;

    private Employee user;

    public void setUser(Employee employee){
        user = employee;
        setWindow();
    }

    public void setWindow(){
        statusBar.setText("Logged as: "+user.getLogin()+" ("+user.getRole().toString()+")");
    }

    @FXML
    void logOut(MouseEvent event) throws IOException {
        Stage actualStage = (Stage) MainBorderPane.getScene().getWindow();
        actualStage.close();

        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        newStage.setScene(new Scene(root));
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.show();
    }

    @FXML
    void toGasTanks(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/GasTanks.fxml"));
        MainBorderPane.setCenter(two);
    }

    @FXML
    void toOne(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/One.fxml"));
        MainBorderPane.setCenter(two);
    }

    @FXML
    void toThree(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/Three.fxml"));
        MainBorderPane.setCenter(two);
    }

    @FXML
    void toTwo(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/Two.fxml"));
        MainBorderPane.setCenter(two);
    }

}
