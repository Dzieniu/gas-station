package com.dzieniu2.controller.employee;

import com.dzieniu2.controller.MainController;
import com.dzieniu2.controller.employee.transaction.TransactionController;
import com.dzieniu2.other.DispenserList;
import com.dzieniu2.other.FuelDispenser;
import com.dzieniu2.repository.ContainerRepository;
import com.jfoenix.controls.JFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;

@Data
public class EmployeeController {

    @FXML
    private BorderPane mainBorderPane,transactionPane;

    @FXML
    private BorderPane cameraPane;

    @FXML
    private Button dispenserButton;

    private MainController mainController;

    private TransactionController transactionController;

    private DispenserList dispenserList;

    @FXML
    public void initialize() throws IOException {

        openDefaultWindow();

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
    public void openDefaultWindow(){

        BorderPane imagePane = new BorderPane();
        ImageView imageView = new ImageView(new Image("images/station.jpg"));
        imagePane.setStyle("-fx-padding: 100 100 100 100");
        imagePane.setCenter(imageView);
        mainBorderPane.setCenter(imagePane);
    }

    @FXML
    public void openProductList() throws IOException {
        JFXScrollPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/ProductList.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    public void openAddClient() throws IOException {
        BorderPane two = FXMLLoader.load(getClass().getResource("/fxml/employee/AddClient.fxml"));
        mainBorderPane.setCenter(two);
    }

    @FXML
    public void openDispenser() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/Dispenser.fxml"));
        BorderPane pane = loader.load();
        DispenserController stationController = loader.getController();
        stationController.setFuelDispenser(dispenserList.getDispensers().get(dispenserList.getSelected()));
        mainBorderPane.setCenter(pane);
    }

    @FXML
    public void openTransactionWindow() {

        mainBorderPane.setCenter(transactionPane);
        transactionController.getDispenserController().refreshData();
        transactionController.getSummaryController().refreshData();
    }

    @FXML
    public void previousDispenser(){
        dispenserButton.setText(dispenserList.previous().getContainer().getFuel().getName());
    }

    @FXML
    public void nextDispenser(){
        dispenserButton.setText(dispenserList.next().getContainer().getFuel().getName());
    }

    private void loadCamera() throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/fxml/employee/Camera.fxml"));
        cameraPane.setCenter(pane);
    }

    public void setMainController(MainController mainController) throws IOException {
        this.mainController = mainController;
        initTransaction();
    }

    public void initTransaction() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/Transaction.fxml"));
        transactionPane = loader.load();
        transactionController = loader.getController();
        transactionController.setEmployeeController(this);
    }
}
