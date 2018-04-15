package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.Transaction;
import com.dzieniu2.repository.CustomerRepository;
import com.dzieniu2.repository.EmployeeRepository;
import com.dzieniu2.repository.FuelRepository;
import com.dzieniu2.repository.TransactionRepository;
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

    private Transaction selectedTransaction;

    @FXML
    public void createTransaction(){
        TransactionRepository transactionRepository = new TransactionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        FuelRepository fuelRepository = new FuelRepository();
        Transaction transaction = new Transaction();
        transaction.setTotalPrice(Double.parseDouble(totalPriceFieldCreate.getText()));
        transaction.setFuelQuantity(Double.parseDouble(fuelQuantityFieldCreate.getText()));
        transaction.setFuelPrice(Double.parseDouble(fuelPriceFieldCreate.getText()));
        transaction.setEmployee(employeeRepository.findById(Long.parseLong(employeeIdFieldCreate.getSelectionModel().getSelectedItem().toString())));
        transaction.setCustomer(customerRepository.findById(Long.parseLong(customerIdFieldCreate.getSelectionModel().getSelectedItem().toString())));
        transaction.setFuel(fuelRepository.findByName(fuelIdFieldCreate.getSelectionModel().getSelectedItem().toString()));
        transactionRepository.add(transaction);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void updateTransaction(){
        TransactionRepository transactionRepository = new TransactionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        FuelRepository fuelRepository = new FuelRepository();
        selectedTransaction.setTotalPrice(Double.parseDouble(totalPriceFieldUpdate.getText()));
        selectedTransaction.setFuelQuantity(Double.parseDouble(fuelQuantityFieldUpdate.getText()));
        selectedTransaction.setFuelPrice(Double.parseDouble(fuelPriceFieldUpdate.getText()));
        selectedTransaction.setEmployee(employeeRepository.findById(Long.parseLong(employeeIdFieldUpdate.getSelectionModel().getSelectedItem().toString())));
        selectedTransaction.setCustomer(customerRepository.findById(Long.parseLong(customerIdFieldUpdate.getSelectionModel().getSelectedItem().toString())));
        selectedTransaction.setFuel(fuelRepository.findByName(fuelIdFieldUpdate.getSelectionModel().getSelectedItem().toString()));
        transactionRepository.update(selectedTransaction);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void deleteTransaction(){
        TransactionRepository transactionRepository = new TransactionRepository();
        transactionRepository.delete(selectedTransaction.getId());
        adminController.switchTab();
        closeWindow();
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setSelectedTransaction(Transaction selectedTransaction) {
        this.selectedTransaction = selectedTransaction;

        EmployeeRepository employeeRepository = new EmployeeRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        FuelRepository fuelRepository = new FuelRepository();
        employeeRepository.findAll().forEach(x -> employeeIdFieldCreate.getItems().add(x.getId()));
        customerRepository.findAll().forEach(x -> customerIdFieldCreate.getItems().add(x.getId()));
        fuelRepository.findAll().forEach(x -> fuelIdFieldCreate.getItems().add(x.getName()));
        employeeRepository.findAll().forEach(x -> employeeIdFieldUpdate.getItems().add(x.getId()));
        customerRepository.findAll().forEach(x -> customerIdFieldUpdate.getItems().add(x.getId()));
        fuelRepository.findAll().forEach(x -> fuelIdFieldUpdate.getItems().add(x.getName()));

        idBox.setText(selectedTransaction.getId().toString());
        totalPriceFieldUpdate.setText(selectedTransaction.getTotalPrice().toString());
        fuelQuantityFieldUpdate.setText(selectedTransaction.getFuelQuantity().toString());
        fuelPriceFieldUpdate.setText(selectedTransaction.getFuelPrice().toString());
        employeeIdFieldUpdate.getSelectionModel().select(selectedTransaction.getEmployee().getId());
        customerIdFieldUpdate.getSelectionModel().select(selectedTransaction.getCustomer().getId());
        fuelIdFieldUpdate.getSelectionModel().select(selectedTransaction.getFuel().getName());
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) transactionPane.getScene().getWindow();
        stage.close();
    }
}
