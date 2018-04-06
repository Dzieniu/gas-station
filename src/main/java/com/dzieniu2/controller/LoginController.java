package com.dzieniu2.controller;

import com.dzieniu2.entity.Employee;
import com.dzieniu2.repository.EmployeeRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    void exit(MouseEvent event) {
        Stage stage = (Stage) LoginWindow.getScene().getWindow();
        stage.close();
    }

    @FXML
    void logIn(MouseEvent event) throws IOException {

        EmployeeRepository employeeRepository = new EmployeeRepository();
        String login = loginField.getText();
        String password = passwordField.getText();
        Employee employee = null;

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

        exit(null);
        showMainWindow(employee);
    }

    @FXML
    public void showMainWindow(Employee employee) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainBorderPane.fxml"));

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/MainBorderPane.fxml"
                )
        );
        Stage stage = new Stage();
        stage.setScene(new Scene((Pane) loader.load()));
        stage.setMaximized(true);
        MainController mainController = loader.<MainController>getController();
        mainController.setUser(employee);

        stage.show();
    }
}
