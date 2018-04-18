package com.dzieniu2.controller;

import com.dzieniu2.entity.Employee;
import com.dzieniu2.repository.EmployeeRepository;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.persistence.NoResultException;
import java.io.IOException;

public class LoginController {

    @FXML
    private VBox LoginWindow;

    @FXML
    private TextField loginField, passwordField;

    @FXML
    private Label infoLabel;

    @FXML
    void logIn() throws IOException {

        EmployeeRepository employeeRepository = new EmployeeRepository();
        String login = loginField.getText();
        String password = passwordField.getText();
        Employee employee;

        try{
            employee = employeeRepository.findByLogin(login);
        }catch (NoResultException e){
            infoLabel.setText("User doesn't exist");
            return;
        }

        if(!employee.getPassword().matches(password)) {
            infoLabel.setText("Incorrect password");
            return;
        }

        closeWindow();
        showMainWindow(employee);
    }

    @FXML
    void exit() {
        closeWindow();
        System.exit(0);
    }

    public void showMainWindow(Employee employee) throws IOException {

        JFXButton jfxButton = new JFXButton();
        jfxButton.setButtonType(JFXButton.ButtonType.RAISED);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/MainBorderPane.fxml"
                )
        );
        Stage stage = new Stage();
        stage.setTitle("gas-station");
        stage.setScene(new Scene((Pane) loader.load()));
        stage.setMaximized(true);
        MainController mainController = loader.<MainController>getController();
        mainController.setEmployee(employee);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        stage.show();
    }

    public void closeWindow(){
        Stage stage = (Stage) LoginWindow.getScene().getWindow();
        stage.close();
    }
}
