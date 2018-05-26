package com.dzieniu2.controller;

import com.dzieniu2.controller.employee.EmployeeController;
import com.dzieniu2.entity.Employee;
import com.jfoenix.controls.JFXButton;
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
    private Label loggedUser;

    @FXML
    private JFXButton logoutButton;

    private Employee employee;

    @FXML
    public void initialize(){
        logoutButton.setStyle("-fx-background-image: url(/images/logout-icon.png);-fx-background-size: 30 30");
        logoutButton.setDisableVisualFocus(true);
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

    public void closeWindow(){
        Stage stage = (Stage) mainBorderPane.getScene().getWindow();
        stage.close();
    }

    public void setEmployee(Employee employee) throws IOException {
        this.employee = employee;
        setWindow();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setWindow() throws IOException {
        loggedUser.setText("Logged as: "+ employee.getLogin()+" ("+ employee.getRole().toString()+")");
        switch (employee.getRole()){
            case ADMIN:
                BorderPane pane1 = FXMLLoader.load(getClass().getResource("/fxml/admin/AdminPanel.fxml"));
                mainBorderPane.setCenter(pane1);
                break;
            case EMPLOYEE:
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/EmployeePanel.fxml"));
                BorderPane pane = loader.load();
                EmployeeController employeeController = loader.getController();
                employeeController.setMainController(this);
                mainBorderPane.setCenter(pane);
                break;
        }
    }
}
