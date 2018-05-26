package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.controller.employee.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;

@Data
public class TransactionController {

    @FXML
    private BorderPane transactionPane;

    private BorderPane productPane,dispenserPane, customerPane;

    private EmployeeController employeeController;

    private DispenserController dispenserController;

    private ShoppingListController shoppingListControllerController;

    private CustomerController customerController;

    private SummaryController summaryController;

    private ArrayList<ShoppingContainerController> chosenProducts;

    @FXML
    public void initialize() { }

    @FXML
    public void openProductWindow(){

        transactionPane.setCenter(productPane);
    }

    @FXML
    public void openDispenserWindow() {

        transactionPane.setCenter(dispenserPane);
    }

    @FXML
    public void openCustomerWindow() {

        transactionPane.setCenter(customerPane);
    }

    @FXML
    public void openSummaryWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/Summary.fxml"));
        BorderPane pane = loader.load();
        summaryController = loader.getController();
        summaryController.setTransactionController(this);
        transactionPane.setCenter(pane);
    }

    public void setEmployeeController(EmployeeController employeeController) throws IOException {
        this.employeeController = employeeController;

        chosenProducts = new ArrayList<>();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/ShoppingList.fxml"));
        productPane = loader.load();
        shoppingListControllerController = loader.getController();
        shoppingListControllerController.setTransactionController(this);

        loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/DispenserChooser.fxml"));
        dispenserPane = loader.load();
        dispenserController = loader.getController();
        dispenserController.setTransactionController(this);

        loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/ClientChooser.fxml"));
        customerPane = loader.load();
        customerController = loader.getController();
        customerController.setTransactionController(this);

        loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/Summary.fxml"));
        BorderPane pane = loader.load();
        summaryController = loader.getController();
        summaryController.setTransactionController(this);

        openProductWindow();
    }

    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    public ArrayList<ShoppingContainerController> getChosenProducts() {
        return chosenProducts;
    }
}
