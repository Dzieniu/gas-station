package com.dzieniu2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private VBox LoginWindow;

    @FXML
    void exit(MouseEvent event) {
        Stage stage = (Stage) LoginWindow.getScene().getWindow();
        stage.close();
    }

    @FXML
    void logIn(MouseEvent event) throws IOException {
        Stage actualStage = (Stage) LoginWindow.getScene().getWindow();

        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainBorderPane.fxml"));
        newStage.setScene(new Scene(root));
        newStage.setMaximized(true);
        newStage.show();

        actualStage.close();
    }

}
