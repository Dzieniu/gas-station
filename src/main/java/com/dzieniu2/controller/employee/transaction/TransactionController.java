package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.controller.employee.*;
import com.dzieniu2.entity.Customer;
import com.dzieniu2.other.FuelDispenser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;

public class TransactionController {

    @FXML
    private BorderPane borderPane;

    private BorderPane productsPane;

    @FXML
    private EmployeeController employeeController;

    @FXML
    private Customer customer;

    @FXML
    private FuelDispenser fuelDispenser;

    @FXML
    private ArrayList<ShoppingContainerController> shoppingCart;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/ShoppingList.fxml"));
        productsPane = loader.load();
        ShoppingList shoppingList = loader.<ShoppingList>getController();
        shoppingList.setTransactionController(this);

        toProducts();
        shoppingCart = new ArrayList<>();
    }

    @FXML
    public void toProducts() throws IOException {

        borderPane.setCenter(productsPane);
    }

    @FXML
    public void toDispensers() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/DispenserChooser.fxml"));
        BorderPane pane = loader.load();
        DispenserChooserController dispenserChooserController = loader.<DispenserChooserController>getController();
        dispenserChooserController.addDispensers(employeeController.getDispenserList().getDispensers());
        dispenserChooserController.setTransactionController(this);
        borderPane.setCenter(pane);
    }

    @FXML
    public void toClientCheck() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/ClientChooser.fxml"));
        BorderPane pane = loader.load();
        ClientChooserController clientChooserController = loader.<ClientChooserController>getController();
        clientChooserController.setTransactionController(this);
        borderPane.setCenter(pane);
    }

    @FXML
    public void toSummary() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/Summary.fxml"));
        BorderPane pane = loader.load();
        SummaryController summaryController = loader.<SummaryController>getController();
        summaryController.setTransactionController(this);
        borderPane.setCenter(pane);
    }

    public void setEmployeeController(EmployeeController employeeController){
        this.employeeController = employeeController;
    }

    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public FuelDispenser getFuelDispenser() {
        return fuelDispenser;
    }

    public void setFuelDispenser(FuelDispenser fuelDispenser) {
        this.fuelDispenser = fuelDispenser;
    }

    public ArrayList<ShoppingContainerController> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<ShoppingContainerController> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
