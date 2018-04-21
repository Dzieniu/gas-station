package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.TransactionFuel;
import com.dzieniu2.repository.CustomerRepository;
import com.dzieniu2.repository.EmployeeRepository;
import com.dzieniu2.repository.FuelRepository;
import com.dzieniu2.repository.FuelTransactionRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransactionController {

    @FXML
    private TabPane transactionPane;

    @FXML
    private TextField totalPriceFieldCreate,fuelQuantityFieldCreate,fuelPriceFieldCreate,
        totalPriceFieldUpdate,fuelQuantityFieldUpdate,fuelPriceFieldUpdate;

    @FXML
    private ChoiceBox employeeIdFieldCreate,customerIdFieldCreate,fuelIdFieldCreate,
        employeeIdFieldUpdate,customerIdFieldUpdate,fuelIdFieldUpdate;

    @FXML
    private Label idBox;

    private AdminController adminController;

    private TransactionFuel selectedTransactionFuel;

    @FXML
    public void createTransaction(){
        FuelTransactionRepository fuelTransactionRepository = new FuelTransactionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        FuelRepository fuelRepository = new FuelRepository();
        TransactionFuel transactionFuel = new TransactionFuel();
        transactionFuel.setTotalPrice(Double.parseDouble(totalPriceFieldCreate.getText()));
        transactionFuel.setFuelQuantity(Double.parseDouble(fuelQuantityFieldCreate.getText()));
        transactionFuel.setFuelPrice(Double.parseDouble(fuelPriceFieldCreate.getText()));
        transactionFuel.setEmployee(employeeRepository.findById(Long.parseLong(employeeIdFieldCreate.getSelectionModel().getSelectedItem().toString())));
        transactionFuel.setCustomer(customerRepository.findById(Long.parseLong(customerIdFieldCreate.getSelectionModel().getSelectedItem().toString())));
        transactionFuel.setFuel(fuelRepository.findByName(fuelIdFieldCreate.getSelectionModel().getSelectedItem().toString()));
        fuelTransactionRepository.add(transactionFuel);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void updateTransaction(){
        FuelTransactionRepository fuelTransactionRepository = new FuelTransactionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        FuelRepository fuelRepository = new FuelRepository();
        selectedTransactionFuel.setTotalPrice(Double.parseDouble(totalPriceFieldUpdate.getText()));
        selectedTransactionFuel.setFuelQuantity(Double.parseDouble(fuelQuantityFieldUpdate.getText()));
        selectedTransactionFuel.setFuelPrice(Double.parseDouble(fuelPriceFieldUpdate.getText()));
        selectedTransactionFuel.setEmployee(employeeRepository.findById(Long.parseLong(employeeIdFieldUpdate.getSelectionModel().getSelectedItem().toString())));
        selectedTransactionFuel.setCustomer(customerRepository.findById(Long.parseLong(customerIdFieldUpdate.getSelectionModel().getSelectedItem().toString())));
        selectedTransactionFuel.setFuel(fuelRepository.findByName(fuelIdFieldUpdate.getSelectionModel().getSelectedItem().toString()));
        fuelTransactionRepository.update(selectedTransactionFuel);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void deleteTransaction(){
        FuelTransactionRepository fuelTransactionRepository = new FuelTransactionRepository();
        fuelTransactionRepository.delete(selectedTransactionFuel.getId());
        adminController.switchTab();
        closeWindow();
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setSelectedTransactionFuel(TransactionFuel selectedTransactionFuel) {
        this.selectedTransactionFuel = selectedTransactionFuel;

        EmployeeRepository employeeRepository = new EmployeeRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        FuelRepository fuelRepository = new FuelRepository();
        employeeRepository.findAll().forEach(x -> employeeIdFieldCreate.getItems().add(x.getId()));
        customerRepository.findAll().forEach(x -> customerIdFieldCreate.getItems().add(x.getId()));
        fuelRepository.findAll().forEach(x -> fuelIdFieldCreate.getItems().add(x.getName()));
        employeeRepository.findAll().forEach(x -> employeeIdFieldUpdate.getItems().add(x.getId()));
        customerRepository.findAll().forEach(x -> customerIdFieldUpdate.getItems().add(x.getId()));
        fuelRepository.findAll().forEach(x -> fuelIdFieldUpdate.getItems().add(x.getName()));

        idBox.setText(selectedTransactionFuel.getId().toString());
        totalPriceFieldUpdate.setText(selectedTransactionFuel.getTotalPrice().toString());
        fuelQuantityFieldUpdate.setText(selectedTransactionFuel.getFuelQuantity().toString());
        fuelPriceFieldUpdate.setText(selectedTransactionFuel.getFuelPrice().toString());
        employeeIdFieldUpdate.getSelectionModel().select(selectedTransactionFuel.getEmployee().getId());
        customerIdFieldUpdate.getSelectionModel().select(selectedTransactionFuel.getCustomer().getId());
        fuelIdFieldUpdate.getSelectionModel().select(selectedTransactionFuel.getFuel().getName());
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) transactionPane.getScene().getWindow();
        stage.close();
    }
}
