package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.Container;
import com.dzieniu2.repository.ContainerRepository;
import com.dzieniu2.repository.FuelRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ContainerController {

    @FXML
    private TabPane containerPane;

    @FXML
    private TextField fuelLeftFieldCreate,capacityFieldCreate,fuelLeftFieldUpdate,capacityFieldUpdate;

    @FXML
    private ChoiceBox fuelBoxCreate,fuelBoxUpdate;

    @FXML
    private Label idBox;

    private AdminController adminController;

    private Container selectedContainer;

    @FXML
    public void createContainer(){
        ContainerRepository containerRepository = new ContainerRepository();
        FuelRepository fuelRepository = new FuelRepository();
        Container container = new Container();
        container.setFuelLeft(Double.parseDouble(fuelLeftFieldCreate.getText()));
        container.setMaxCapacity(Long.parseLong(capacityFieldCreate.getText()));
        container.setFuel(fuelRepository.findByName((String) fuelBoxCreate.getSelectionModel().getSelectedItem()));
        containerRepository.add(container);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void updateContainer(){
        ContainerRepository containerRepository = new ContainerRepository();
        FuelRepository fuelRepository = new FuelRepository();
        selectedContainer.setFuelLeft(Double.parseDouble(fuelLeftFieldUpdate.getText()));
        selectedContainer.setMaxCapacity(Long.parseLong(capacityFieldUpdate.getText()));
        selectedContainer.setFuel(fuelRepository.findByName((String) fuelBoxUpdate.getSelectionModel().getSelectedItem()));
        containerRepository.update(selectedContainer);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void deleteContainer(){
        ContainerRepository containerRepository = new ContainerRepository();
        containerRepository.delete(selectedContainer.getId());
        adminController.switchTab();
        closeWindow();
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setSelectedContainer(Container selectedContainer) {
        this.selectedContainer = selectedContainer;

        FuelRepository fuelRepository = new FuelRepository();
        fuelRepository.findAll().forEach(x -> fuelBoxCreate.getItems().add(x.getName()));
        fuelRepository.findAll().forEach(x -> fuelBoxUpdate.getItems().add(x.getName()));

        idBox.setText(selectedContainer.getId().toString());
        fuelLeftFieldUpdate.setText(selectedContainer.getFuelLeft().toString());
        capacityFieldUpdate.setText(selectedContainer.getMaxCapacity().toString());
        fuelBoxUpdate.getSelectionModel().select(selectedContainer.getFuel().getName());
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) containerPane.getScene().getWindow();
        stage.close();
    }
}
