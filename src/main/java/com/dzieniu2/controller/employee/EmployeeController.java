package com.dzieniu2.controller.employee;

import com.dzieniu2.controller.MainController;
import com.dzieniu2.controller.employee.transaction.TransactionController;
import com.dzieniu2.other.DispenserList;
import com.dzieniu2.other.FuelDispenser;
import com.dzieniu2.repository.ContainerRepository;
import com.jfoenix.controls.JFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeeController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private BorderPane cameraPane;

    @FXML
    private Button dispenserButton;

    private MainController mainController;

    private DispenserList dispenserList;

    @FXML
    public void initialize() throws IOException {

        toStart();

        ContainerRepository containerRepository = new ContainerRepository();
        ArrayList<FuelDispenser> dispensers = new ArrayList<>();
        dispensers.add(new FuelDispenser(containerRepository.findById(1l)));
        dispensers.add(new FuelDispenser(containerRepository.findById(2l)));
        dispensers.add(new FuelDispenser(containerRepository.findById(3l)));
        dispensers.add(new FuelDispenser(containerRepository.findById(4l)));
        dispenserList = new DispenserList(dispensers);
        nextDispenser();

        loadCamera();
    }

    @FXML
    public void toStart(){

        BorderPane imagePane = new BorderPane();
        ImageView imageView = new ImageView(new Image("images/station.jpg"));
        imagePane.setStyle("-fx-padding: 100 100 100 100");
        imagePane.setCenter(imageView);
        mainBorderPane.setCenter(imagePane);
    }

    @FXML
    public void toGasTanks(MouseEvent event) throws IOException {
        GridPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/GasTanks.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    public void toProductList(MouseEvent event) throws IOException {
        JFXScrollPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/ProductList.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    public void toThree(MouseEvent event) throws IOException {
        BorderPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/AddClient.fxml"));
        mainBorderPane.setCenter(two);
    }

    private void loadCamera() throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/fxml/employee/CameraPane.fxml"));
        cameraPane.setCenter(pane);
    }

    @FXML
    public void toDispenser(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/Dispenser.fxml"));
        BorderPane pane = loader.load();
        DispenserController stationController = loader.<DispenserController>getController();
        stationController.setFuelDispenser(dispenserList.getDispensers().get(dispenserList.getSelected()));
        mainBorderPane.setCenter(pane);
    }

    @FXML
    public void previousDispenser(){
        dispenserButton.setText(dispenserList.previous().getContainer().getFuel().getName());
    }

    @FXML
    public void nextDispenser(){
        dispenserButton.setText(dispenserList.next().getContainer().getFuel().getName());
    }

    @FXML
    public void toTransaction() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/Transaction.fxml"));
        BorderPane pane = loader.load();
        TransactionController transactionController = loader.<TransactionController>getController();
        transactionController.setEmployeeController(this);
        mainBorderPane.setCenter(pane);
    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public MainController getMainController() {
        return mainController;
    }

    public DispenserList getDispenserList() {
        return dispenserList;
    }
}
