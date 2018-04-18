package com.dzieniu2.controller;

import com.dzieniu2.entity.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Label statusBar;

    private Employee employee;

    @FXML
    public void initialize(){
    }

    @FXML
    void logOut() throws IOException {

        closeWindow();

        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        newStage.setScene(new Scene(root));
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.show();
    }

    @FXML
    void exit() {
        closeWindow();
        System.exit(0);
    }

    public void closeWindow(){
        Stage stage = (Stage) mainBorderPane.getScene().getWindow();
        stage.close();
    }

    public void setEmployee(Employee employee) throws IOException {
        this.employee = employee;
        setWindow();
    }

    public void setWindow() throws IOException {
        statusBar.setText("Logged as: "+ employee.getLogin()+" ("+ employee.getRole().toString()+")");
        switch (employee.getRole()){
            case ADMIN:
                BorderPane pane1 = FXMLLoader.load(getClass().getResource("/fxml/admin/AdminPanel.fxml"));
                mainBorderPane.setCenter(pane1);
                break;
            case EMPLOYEE:
                BorderPane pane2 = FXMLLoader.load(getClass().getResource("/fxml/employee/EmployeePanel.fxml"));
                mainBorderPane.setCenter(pane2);
                break;
        }
    }
}
