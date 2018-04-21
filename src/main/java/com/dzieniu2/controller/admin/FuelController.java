package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.Fuel;
import com.dzieniu2.repository.FuelRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FuelController {

    @FXML
    private TabPane fuelPane;

    @FXML
    TextField nameFieldCreate,priceFieldCreate,nameFieldUpdate,priceFieldUpdate;

    @FXML
    private Label idBox;

    private AdminController adminController;

    private Fuel selectedFuel;

    @FXML
    public void createFuel(){
        FuelRepository fuelRepository = new FuelRepository();
        Fuel fuel = new Fuel();
        fuel.setName(nameFieldCreate.getText());
        fuel.setPrice(Double.parseDouble(priceFieldCreate.getText()));
        fuelRepository.add(fuel);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void updateFuel(){
        FuelRepository fuelRepository = new FuelRepository();
        selectedFuel.setName(nameFieldUpdate.getText());
        selectedFuel.setPrice(Double.parseDouble(priceFieldUpdate.getText()));
        fuelRepository.update(selectedFuel);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void deleteFuel(){
        FuelRepository fuelRepository = new FuelRepository();
        fuelRepository.delete(selectedFuel.getId());
        adminController.switchTab();
        closeWindow();
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setSelectedFuel(Fuel selectedFuel) {
        this.selectedFuel = selectedFuel;

        idBox.setText(selectedFuel.getId().toString());
        nameFieldUpdate.setText(selectedFuel.getName());
        priceFieldUpdate.setText(selectedFuel.getPrice().toString());
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) fuelPane.getScene().getWindow();
        stage.close();
    }
}
